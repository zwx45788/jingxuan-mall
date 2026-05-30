package com.example.shopping.service;

import com.example.shopping.api.MerchantServiceGrpc;
import com.example.shopping.api.Shopping;
import com.example.shopping.config.JwtConfig;
import com.example.shopping.model.Merchant;
import com.example.shopping.model.Shop;
import com.example.shopping.repository.MerchantRepository;
import com.example.shopping.repository.ShopRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@GrpcService
public class MerchantServiceImpl extends MerchantServiceGrpc.MerchantServiceImplBase {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    ShopRepository shopRepository;
    @Override
    public  void registerMerchant(Shopping.RegisterMerchantRequest request, StreamObserver<Shopping.RegisterMerchantResponse> responseStreamObserver){
        try{
            boolean exist = merchantRepository.existsByEmail(request.getEmail());
            if (exist){
                responseStreamObserver.onNext(
                        Shopping.RegisterMerchantResponse.newBuilder()
                                .setSuccess(false)
                                .setMessage("该用户已存在")
                                .build()
                );
            }
            Merchant merchant = Merchant.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .build();
            merchantRepository.save(merchant);

            responseStreamObserver.onNext(
                    Shopping.RegisterMerchantResponse.newBuilder()
                            .setSuccess(true)
                            .setMessage("商户注册成功!")
                            .build()
            );
            responseStreamObserver.onCompleted();
        }catch (Exception e){
            log.error("商户注册失败:{}",e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }

    }

    @Override
    public void loginMerchant(Shopping.LoginMerchantRequest request, StreamObserver<Shopping.LoginMerchantResponse> responseStreamObserver) {
        try{
           merchantRepository.findByEmail(request.getEmail()).ifPresentOrElse(
                   merchant -> {
                       if(passwordEncoder.matches(request.getPassword(), merchant.getPassword())){

                            responseStreamObserver.onNext(
                                    Shopping.LoginMerchantResponse.newBuilder()
                                            .setSuccess(true)
                                            .setMessage("商户登录成功")
                                            .setToken(jwtTokenProvider.generateToken(merchant.getId()))
                                            .build()
                            );
                       }
                       else{
                           responseStreamObserver.onNext(
                                   Shopping.LoginMerchantResponse.newBuilder()
                                           .setSuccess(false)
                                           .setMessage("密码错误")
                                           .build()
                           );
                       }

           },
                   ()->{
                       responseStreamObserver.onNext(
                               Shopping.LoginMerchantResponse.newBuilder()
                                       .setSuccess(false)
                                       .setMessage("该用户不存在")
                                       .build()
                       );
                   }
           );
            responseStreamObserver.onCompleted();

        }
        catch (Exception e){
            log.error("商户登录失败");
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }

    }
    @Override
    public void createShop(Shopping.CreateShopRequest request,StreamObserver<Shopping.CreateShopResponse> responseStreamObserver){
        try{
            shopRepository.findByMerchantIdAndShopName(request.getMerchantId(), request.getShopName()).ifPresentOrElse(
                    shop -> {
                        responseStreamObserver.onNext(
                                Shopping.CreateShopResponse.newBuilder()
                                        .setSuccess(false)
                                        .setMessage("该店铺已存在")
                                        .build()
                        );
                    },
                            ()->{
                                Shop shop = Shop.builder()
                                        .merchantId(request.getMerchantId())
                                        .shopName(request.getShopName())
                                        .description(request.getDescription())
                                        .logo(request.getLogo())
                                        .build();
                                shopRepository.save(shop);
                                responseStreamObserver.onNext(
                                        Shopping.CreateShopResponse.newBuilder()
                                                .setSuccess(true)
                                                .setMessage("创建店铺成功")
                                                .build()
                                );
                            }
            );
            responseStreamObserver.onCompleted();

        }catch (Exception e){
            log.error("商铺创建失败");
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }

    }
}
