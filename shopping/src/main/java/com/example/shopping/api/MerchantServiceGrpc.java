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
public final class MerchantServiceGrpc {

  private MerchantServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.shopping.MerchantService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.RegisterMerchantRequest,
      com.example.shopping.api.Shopping.RegisterMerchantResponse> METHOD_REGISTER_MERCHANT =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.RegisterMerchantRequest, com.example.shopping.api.Shopping.RegisterMerchantResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "RegisterMerchant"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RegisterMerchantRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RegisterMerchantResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.LoginMerchantRequest,
      com.example.shopping.api.Shopping.LoginMerchantResponse> METHOD_LOGIN_MERCHANT =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.LoginMerchantRequest, com.example.shopping.api.Shopping.LoginMerchantResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "LoginMerchant"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.LoginMerchantRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.LoginMerchantResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.CreateProductRequest,
      com.example.shopping.api.Shopping.CreateProductResponse> METHOD_CREATE_PRODUCT =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.CreateProductRequest, com.example.shopping.api.Shopping.CreateProductResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "CreateProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.CreateProductRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.CreateProductResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.PublishProductRequest,
      com.example.shopping.api.Shopping.PublishProductResponse> METHOD_PUBLISH_PRODUCT =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.PublishProductRequest, com.example.shopping.api.Shopping.PublishProductResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "PublishProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.PublishProductRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.PublishProductResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.UnPublishProductRequest,
      com.example.shopping.api.Shopping.UnPublishProductResponse> METHOD_UN_PUBLISH_PRODUCT =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.UnPublishProductRequest, com.example.shopping.api.Shopping.UnPublishProductResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "UnPublishProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.UnPublishProductRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.UnPublishProductResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.CreateShopRequest,
      com.example.shopping.api.Shopping.CreateShopResponse> METHOD_CREATE_SHOP =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.CreateShopRequest, com.example.shopping.api.Shopping.CreateShopResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "CreateShop"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.CreateShopRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.CreateShopResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.GetMerchantInfoRequest,
      com.example.shopping.api.Shopping.GetMerchantInfoResponse> METHOD_GET_MERCHANT_INFO =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.GetMerchantInfoRequest, com.example.shopping.api.Shopping.GetMerchantInfoResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.MerchantService", "GetMerchantInfo"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.GetMerchantInfoRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.GetMerchantInfoResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MerchantServiceStub newStub(io.grpc.Channel channel) {
    return new MerchantServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MerchantServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MerchantServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MerchantServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MerchantServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MerchantServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerMerchant(com.example.shopping.api.Shopping.RegisterMerchantRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterMerchantResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_MERCHANT, responseObserver);
    }

    /**
     */
    public void loginMerchant(com.example.shopping.api.Shopping.LoginMerchantRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginMerchantResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOGIN_MERCHANT, responseObserver);
    }

    /**
     */
    public void createProduct(com.example.shopping.api.Shopping.CreateProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateProductResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_PRODUCT, responseObserver);
    }

    /**
     */
    public void publishProduct(com.example.shopping.api.Shopping.PublishProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.PublishProductResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PUBLISH_PRODUCT, responseObserver);
    }

    /**
     */
    public void unPublishProduct(com.example.shopping.api.Shopping.UnPublishProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.UnPublishProductResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UN_PUBLISH_PRODUCT, responseObserver);
    }

    /**
     */
    public void createShop(com.example.shopping.api.Shopping.CreateShopRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateShopResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_SHOP, responseObserver);
    }

    /**
     */
    public void getMerchantInfo(com.example.shopping.api.Shopping.GetMerchantInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetMerchantInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MERCHANT_INFO, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REGISTER_MERCHANT,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.RegisterMerchantRequest,
                com.example.shopping.api.Shopping.RegisterMerchantResponse>(
                  this, METHODID_REGISTER_MERCHANT)))
          .addMethod(
            METHOD_LOGIN_MERCHANT,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.LoginMerchantRequest,
                com.example.shopping.api.Shopping.LoginMerchantResponse>(
                  this, METHODID_LOGIN_MERCHANT)))
          .addMethod(
            METHOD_CREATE_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.CreateProductRequest,
                com.example.shopping.api.Shopping.CreateProductResponse>(
                  this, METHODID_CREATE_PRODUCT)))
          .addMethod(
            METHOD_PUBLISH_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.PublishProductRequest,
                com.example.shopping.api.Shopping.PublishProductResponse>(
                  this, METHODID_PUBLISH_PRODUCT)))
          .addMethod(
            METHOD_UN_PUBLISH_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.UnPublishProductRequest,
                com.example.shopping.api.Shopping.UnPublishProductResponse>(
                  this, METHODID_UN_PUBLISH_PRODUCT)))
          .addMethod(
            METHOD_CREATE_SHOP,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.CreateShopRequest,
                com.example.shopping.api.Shopping.CreateShopResponse>(
                  this, METHODID_CREATE_SHOP)))
          .addMethod(
            METHOD_GET_MERCHANT_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.GetMerchantInfoRequest,
                com.example.shopping.api.Shopping.GetMerchantInfoResponse>(
                  this, METHODID_GET_MERCHANT_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class MerchantServiceStub extends io.grpc.stub.AbstractStub<MerchantServiceStub> {
    private MerchantServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MerchantServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MerchantServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MerchantServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerMerchant(com.example.shopping.api.Shopping.RegisterMerchantRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterMerchantResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_MERCHANT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void loginMerchant(com.example.shopping.api.Shopping.LoginMerchantRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginMerchantResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGIN_MERCHANT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProduct(com.example.shopping.api.Shopping.CreateProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateProductResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void publishProduct(com.example.shopping.api.Shopping.PublishProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.PublishProductResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PUBLISH_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unPublishProduct(com.example.shopping.api.Shopping.UnPublishProductRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.UnPublishProductResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UN_PUBLISH_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createShop(com.example.shopping.api.Shopping.CreateShopRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateShopResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_SHOP, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMerchantInfo(com.example.shopping.api.Shopping.GetMerchantInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetMerchantInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MERCHANT_INFO, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MerchantServiceBlockingStub extends io.grpc.stub.AbstractStub<MerchantServiceBlockingStub> {
    private MerchantServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MerchantServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MerchantServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MerchantServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.shopping.api.Shopping.RegisterMerchantResponse registerMerchant(com.example.shopping.api.Shopping.RegisterMerchantRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_MERCHANT, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.LoginMerchantResponse loginMerchant(com.example.shopping.api.Shopping.LoginMerchantRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOGIN_MERCHANT, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.CreateProductResponse createProduct(com.example.shopping.api.Shopping.CreateProductRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.PublishProductResponse publishProduct(com.example.shopping.api.Shopping.PublishProductRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PUBLISH_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.UnPublishProductResponse unPublishProduct(com.example.shopping.api.Shopping.UnPublishProductRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UN_PUBLISH_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.CreateShopResponse createShop(com.example.shopping.api.Shopping.CreateShopRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_SHOP, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.GetMerchantInfoResponse getMerchantInfo(com.example.shopping.api.Shopping.GetMerchantInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MERCHANT_INFO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MerchantServiceFutureStub extends io.grpc.stub.AbstractStub<MerchantServiceFutureStub> {
    private MerchantServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MerchantServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MerchantServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MerchantServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.RegisterMerchantResponse> registerMerchant(
        com.example.shopping.api.Shopping.RegisterMerchantRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_MERCHANT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.LoginMerchantResponse> loginMerchant(
        com.example.shopping.api.Shopping.LoginMerchantRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGIN_MERCHANT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.CreateProductResponse> createProduct(
        com.example.shopping.api.Shopping.CreateProductRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.PublishProductResponse> publishProduct(
        com.example.shopping.api.Shopping.PublishProductRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PUBLISH_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.UnPublishProductResponse> unPublishProduct(
        com.example.shopping.api.Shopping.UnPublishProductRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UN_PUBLISH_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.CreateShopResponse> createShop(
        com.example.shopping.api.Shopping.CreateShopRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_SHOP, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.GetMerchantInfoResponse> getMerchantInfo(
        com.example.shopping.api.Shopping.GetMerchantInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MERCHANT_INFO, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_MERCHANT = 0;
  private static final int METHODID_LOGIN_MERCHANT = 1;
  private static final int METHODID_CREATE_PRODUCT = 2;
  private static final int METHODID_PUBLISH_PRODUCT = 3;
  private static final int METHODID_UN_PUBLISH_PRODUCT = 4;
  private static final int METHODID_CREATE_SHOP = 5;
  private static final int METHODID_GET_MERCHANT_INFO = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MerchantServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MerchantServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_MERCHANT:
          serviceImpl.registerMerchant((com.example.shopping.api.Shopping.RegisterMerchantRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RegisterMerchantResponse>) responseObserver);
          break;
        case METHODID_LOGIN_MERCHANT:
          serviceImpl.loginMerchant((com.example.shopping.api.Shopping.LoginMerchantRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.LoginMerchantResponse>) responseObserver);
          break;
        case METHODID_CREATE_PRODUCT:
          serviceImpl.createProduct((com.example.shopping.api.Shopping.CreateProductRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateProductResponse>) responseObserver);
          break;
        case METHODID_PUBLISH_PRODUCT:
          serviceImpl.publishProduct((com.example.shopping.api.Shopping.PublishProductRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.PublishProductResponse>) responseObserver);
          break;
        case METHODID_UN_PUBLISH_PRODUCT:
          serviceImpl.unPublishProduct((com.example.shopping.api.Shopping.UnPublishProductRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.UnPublishProductResponse>) responseObserver);
          break;
        case METHODID_CREATE_SHOP:
          serviceImpl.createShop((com.example.shopping.api.Shopping.CreateShopRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.CreateShopResponse>) responseObserver);
          break;
        case METHODID_GET_MERCHANT_INFO:
          serviceImpl.getMerchantInfo((com.example.shopping.api.Shopping.GetMerchantInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetMerchantInfoResponse>) responseObserver);
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

  private static final class MerchantServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.shopping.api.Shopping.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MerchantServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MerchantServiceDescriptorSupplier())
              .addMethod(METHOD_REGISTER_MERCHANT)
              .addMethod(METHOD_LOGIN_MERCHANT)
              .addMethod(METHOD_CREATE_PRODUCT)
              .addMethod(METHOD_PUBLISH_PRODUCT)
              .addMethod(METHOD_UN_PUBLISH_PRODUCT)
              .addMethod(METHOD_CREATE_SHOP)
              .addMethod(METHOD_GET_MERCHANT_INFO)
              .build();
        }
      }
    }
    return result;
  }
}
