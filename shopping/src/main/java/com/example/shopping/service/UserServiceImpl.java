package com.example.shopping.service;

import com.example.shopping.model.User;
import com.example.shopping.api.*;
import com.example.shopping.model.UserAddress;
import com.example.shopping.pkg.oss.OssService;
import com.example.shopping.repository.UserAddressRepository;
import com.example.shopping.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import jakarta.transaction.Transactional;

import java.util.List;

@Slf4j
@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private OssService ossService;

    @Override
    public void registerUser(Shopping.RegisterUserRequest request, StreamObserver<Shopping.RegisterUserResponse> responseStreamObserver) {
        try {
            log.info("Received RegisterUserRequest: {}", request.getUsername());

            if (userRepository.existsByEmail(request.getEmail())) {
                responseStreamObserver.onNext(
                        Shopping.RegisterUserResponse.newBuilder()
                                .setSuccess(false)
                                .setMessage("该用户已存在")
                                .build()
                );
                return;
            }

            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            userRepository.save(user);

            responseStreamObserver.onNext(
                    Shopping.RegisterUserResponse.newBuilder()
                            .setMessage("用户注册成功")
                            .setSuccess(true)
                            .build()
            );

            responseStreamObserver.onCompleted();
        } catch (Exception e) {
            log.error("用户注册失败: {}", e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }

    @Override
    public void loginUser(Shopping.LoginUserRequest request, StreamObserver<Shopping.LoginUserResponse> responseStreamObserver) {
        try {
            userRepository.findByEmail(request.getEmail()).ifPresentOrElse(
                    user -> {
                        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                            String token = jwtTokenProvider.generateToken(user.getId());
                            responseStreamObserver.onNext(Shopping.LoginUserResponse.newBuilder()
                                    .setMessage("用户登录成功")
                                    .setSuccess(true)
                                    .setToken(token)
                                    .build());
                            responseStreamObserver.onCompleted();
                        } else {
                            responseStreamObserver.onNext(
                                    Shopping.LoginUserResponse.newBuilder()
                                            .setSuccess(false)
                                            .setMessage("密码错误")
                                            .build()
                            );
                        }
                        responseStreamObserver.onCompleted();
                    },
                    () -> {
                        responseStreamObserver.onNext(
                                Shopping.LoginUserResponse.newBuilder()
                                        .setSuccess(false)
                                        .setMessage("用户不存在，请先注册")
                                        .build()
                        );
                        responseStreamObserver.onCompleted();
                    });
            responseStreamObserver.onCompleted();
        } catch (Exception e) {
            log.error("用户登录失败: {}", e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());

        }
    }


    @Override
    public void getUserInfo(Shopping.GetUserInfoRequest request, StreamObserver<Shopping.GetUserInfoResponse> responseStreamObserver) {
        try {
            userRepository.findById(request.getUserId()).ifPresentOrElse(
                    user -> {
                        Shopping.User newUser = Shopping.User.newBuilder()
                                .setUserId(user.getId())
                                .setUsername(user.getUsername())
                                .setEmail(user.getEmail())
                                .build();
                        responseStreamObserver.onNext(
                                Shopping.GetUserInfoResponse.newBuilder()
                                        .setSuccess(true)
                                        .setMessage("查找用户信息成功")
                                        .setUser(newUser)
                                        .build()
                        );

                    },
                    () -> responseStreamObserver.onNext(
                            Shopping.GetUserInfoResponse.newBuilder()
                                    .setSuccess(false)
                                    .setMessage("用户不存在")
                                    .build()
                    )

            );
        } catch (Exception e) {

            log.error("获取用户信息失败: {}", e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());

        }
    }

    @Override
    public void listAddresses(Shopping.ListAddressesRequest request, StreamObserver<Shopping.ListAddressesResponse> responseStreamObserver) {
        try {
            Long userId = request.getUserId();

            List<UserAddress> dbAddress = userAddressRepository.findByUserId(userId);

            List<Shopping.AddressInfo> protoAddresses = dbAddress.stream().map(addr -> {
                String fullAddress = addr.getProvince() + addr.getCity() + addr.getDistrict() + addr.getDetail();
                return Shopping.AddressInfo.newBuilder()
                        .setCity(addr.getCity())
                        .setDistrict(addr.getDistrict())
                        .setProvince(addr.getProvince())
                        .setDetail(addr.getDetail())
                        .setAddressId(addr.getId())
                        .setReceiverName(addr.getReceiverName())
                        .setPhoneNumber(addr.getPhoneNumber())
                        .build();
            }).toList();
            Shopping.ListAddressesResponse response = Shopping.ListAddressesResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("获取用户地址成功")
                    .addAllAddresses(protoAddresses)
                    .build();

            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        } catch (Exception e) {

            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }

    @Override
    @Transactional
    public void updateAddress(Shopping.UpdateAddressRequest request, StreamObserver<Shopping.UpdateAddressResponse> responseStreamObserver) {
        try {
            Long userId = request.getUserId();
            Long addressId = request.getAddressId();
            Shopping.AddressInfo info = request.getAddressInfo();

            UserAddress userAddress = userAddressRepository.findById(addressId)
                    .orElseThrow(() -> new IllegalArgumentException("地址ID不存在"));

            if (!userAddress.getUserId().equals(userId)) {
                throw new SecurityException("无权修改该地址");
            }

            userAddress.setReceiverName(info.getReceiverName());
            userAddress.setPhoneNumber(info.getPhoneNumber());
            userAddress.setProvince(info.getProvince());
            userAddress.setCity(info.getCity());
            userAddress.setDistrict(info.getDistrict());
            userAddress.setDetail(info.getDetail());

            userAddressRepository.save(userAddress);

            Shopping.UpdateAddressResponse response = Shopping.UpdateAddressResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("更新成功")
                    .build();

            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        } catch (Exception e) {
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }
}