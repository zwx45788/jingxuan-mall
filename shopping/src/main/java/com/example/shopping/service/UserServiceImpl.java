package com.example.shopping.service;

import com.example.shopping.config.JwtConfig;
import com.example.shopping.model.User;
import com.example.shopping.api.*;
import com.example.shopping.pkg.oss.OssService;
import com.example.shopping.repository.MerchantRepository;
import com.example.shopping.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;

@Slf4j
@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
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
        try{
            log.info("Received RegisterUserRequest: {}", request.getUsername());
            
            if(userRepository.existsByEmail(request.getEmail())){
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

    public void loginUser(Shopping.LoginUserRequest request, StreamObserver<Shopping.LoginUserResponse> responseStreamObserver) {
        try{
            userRepository.findByEmail(request.getEmail()).ifPresentOrElse(
                    user -> {
                if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
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
            },
                    ()->{
                       responseStreamObserver.onNext(
                               Shopping.LoginUserResponse.newBuilder()
                                       .setSuccess(false)
                                       .setMessage("用户不存在，请先注册")
                                       .build()
                       );
                    });
            responseStreamObserver.onCompleted();
        }
        catch (Exception e) {
            log.error("用户登录失败: {}", e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }
    
    
   
}