package com.example.shopping.service;

import com.example.shopping.model.User;
import com.example.shopping.api.*;
import com.example.shopping.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;

@Slf4j
@GrpcService
public class UserServiceImpl extends UserserviceGrpc.UserserviceImplBase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(Shopping.RegisterUserRequest request, StreamObserver<Shopping.RegisterUserResponse> responseObserver) {
        try{
            log.info("Received RegisterUserRequest: {}", request.getUsername());
            
            if(userRepository.existsByEmail(request.getEmail())){
                responseObserver.onError(Status.ALREADY_EXISTS.withDescription("Email already exists").asException());
                return;
            }

            User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

            userRepository.save(user);

           responseObserver.onNext(
            Shopping.RegisterUserResponse.newBuilder()
                .setMessage("User registered successfully")
                .setSuccess(true)
                .build() 
            ); 

            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }

    public void loginUser(Shopping.LoginUserRequest request, StreamObserver<Shopping.LoginUserResponse> responseObserver) {
        try{
            userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
                if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                    responseObserver.onNext(Shopping.LoginUserResponse.newBuilder().setMessage("Login successful").build());
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid password").asException());
                }
            });
        }
        catch (Exception e) {
            log.error("Error logging in user: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }
}