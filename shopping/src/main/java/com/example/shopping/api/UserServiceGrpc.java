package com.example.shopping.api;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: shopping.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.shopping.UserService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.RegisterUserRequest,
      com.example.shopping.api.Shopping.RegisterUserResponse> METHOD_REGISTER_USER =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.RegisterUserRequest, com.example.shopping.api.Shopping.RegisterUserResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.UserService", "RegisterUser"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RegisterUserRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RegisterUserResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.LoginUserRequest,
      com.example.shopping.api.Shopping.LoginUserResponse> METHOD_LOGIN_USER =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.LoginUserRequest, com.example.shopping.api.Shopping.LoginUserResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.UserService", "LoginUser"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.LoginUserRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.LoginUserResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerUser(com.example.shopping.api.Shopping.RegisterUserRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_USER, responseObserver);
    }

    /**
     */
    public void loginUser(com.example.shopping.api.Shopping.LoginUserRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOGIN_USER, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REGISTER_USER,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.RegisterUserRequest,
                com.example.shopping.api.Shopping.RegisterUserResponse>(
                  this, METHODID_REGISTER_USER)))
          .addMethod(
            METHOD_LOGIN_USER,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.LoginUserRequest,
                com.example.shopping.api.Shopping.LoginUserResponse>(
                  this, METHODID_LOGIN_USER)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerUser(com.example.shopping.api.Shopping.RegisterUserRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_USER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void loginUser(com.example.shopping.api.Shopping.LoginUserRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGIN_USER, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.shopping.api.Shopping.RegisterUserResponse registerUser(com.example.shopping.api.Shopping.RegisterUserRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_USER, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.LoginUserResponse loginUser(com.example.shopping.api.Shopping.LoginUserRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOGIN_USER, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.RegisterUserResponse> registerUser(
        com.example.shopping.api.Shopping.RegisterUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_USER, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.LoginUserResponse> loginUser(
        com.example.shopping.api.Shopping.LoginUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGIN_USER, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_USER = 0;
  private static final int METHODID_LOGIN_USER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_USER:
          serviceImpl.registerUser((com.example.shopping.api.Shopping.RegisterUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterUserResponse>) responseObserver);
          break;
        case METHODID_LOGIN_USER:
          serviceImpl.loginUser((com.example.shopping.api.Shopping.LoginUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginUserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class UserServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.shopping.api.Shopping.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceDescriptorSupplier())
              .addMethod(METHOD_REGISTER_USER)
              .addMethod(METHOD_LOGIN_USER)
              .build();
        }
      }
    }
    return result;
  }
}
