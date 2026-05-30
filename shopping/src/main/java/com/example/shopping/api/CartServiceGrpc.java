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
public final class CartServiceGrpc {

  private CartServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.shopping.CartService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.GetCartInfoRequest,
      com.example.shopping.api.Shopping.GetCartInfoResponse> METHOD_GET_CART_INFO =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.GetCartInfoRequest, com.example.shopping.api.Shopping.GetCartInfoResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.CartService", "GetCartInfo"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.GetCartInfoRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.GetCartInfoResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.AddToCartRequest,
      com.example.shopping.api.Shopping.AddToCartResponse> METHOD_ADD_TO_CART =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.AddToCartRequest, com.example.shopping.api.Shopping.AddToCartResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.CartService", "AddToCart"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.AddToCartRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.AddToCartResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.shopping.api.Shopping.RemoveFromCartRequest,
      com.example.shopping.api.Shopping.RemoveFromCartResponse> METHOD_REMOVE_FROM_CART =
      io.grpc.MethodDescriptor.<com.example.shopping.api.Shopping.RemoveFromCartRequest, com.example.shopping.api.Shopping.RemoveFromCartResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.shopping.CartService", "RemoveFromCart"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RemoveFromCartRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.shopping.api.Shopping.RemoveFromCartResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CartServiceStub newStub(io.grpc.Channel channel) {
    return new CartServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CartServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CartServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CartServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CartServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CartServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCartInfo(com.example.shopping.api.Shopping.GetCartInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetCartInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_CART_INFO, responseObserver);
    }

    /**
     */
    public void addToCart(com.example.shopping.api.Shopping.AddToCartRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.AddToCartResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_TO_CART, responseObserver);
    }

    /**
     */
    public void removeFromCart(com.example.shopping.api.Shopping.RemoveFromCartRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RemoveFromCartResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REMOVE_FROM_CART, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_CART_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.GetCartInfoRequest,
                com.example.shopping.api.Shopping.GetCartInfoResponse>(
                  this, METHODID_GET_CART_INFO)))
          .addMethod(
            METHOD_ADD_TO_CART,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.AddToCartRequest,
                com.example.shopping.api.Shopping.AddToCartResponse>(
                  this, METHODID_ADD_TO_CART)))
          .addMethod(
            METHOD_REMOVE_FROM_CART,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.shopping.api.Shopping.RemoveFromCartRequest,
                com.example.shopping.api.Shopping.RemoveFromCartResponse>(
                  this, METHODID_REMOVE_FROM_CART)))
          .build();
    }
  }

  /**
   */
  public static final class CartServiceStub extends io.grpc.stub.AbstractStub<CartServiceStub> {
    private CartServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CartServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CartServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCartInfo(com.example.shopping.api.Shopping.GetCartInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetCartInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_CART_INFO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addToCart(com.example.shopping.api.Shopping.AddToCartRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.AddToCartResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_TO_CART, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeFromCart(com.example.shopping.api.Shopping.RemoveFromCartRequest request,
        io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RemoveFromCartResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REMOVE_FROM_CART, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CartServiceBlockingStub extends io.grpc.stub.AbstractStub<CartServiceBlockingStub> {
    private CartServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CartServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CartServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.shopping.api.Shopping.GetCartInfoResponse getCartInfo(com.example.shopping.api.Shopping.GetCartInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_CART_INFO, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.AddToCartResponse addToCart(com.example.shopping.api.Shopping.AddToCartRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_TO_CART, getCallOptions(), request);
    }

    /**
     */
    public com.example.shopping.api.Shopping.RemoveFromCartResponse removeFromCart(com.example.shopping.api.Shopping.RemoveFromCartRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REMOVE_FROM_CART, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CartServiceFutureStub extends io.grpc.stub.AbstractStub<CartServiceFutureStub> {
    private CartServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CartServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CartServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.GetCartInfoResponse> getCartInfo(
        com.example.shopping.api.Shopping.GetCartInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_CART_INFO, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.AddToCartResponse> addToCart(
        com.example.shopping.api.Shopping.AddToCartRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_TO_CART, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.shopping.api.Shopping.RemoveFromCartResponse> removeFromCart(
        com.example.shopping.api.Shopping.RemoveFromCartRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REMOVE_FROM_CART, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CART_INFO = 0;
  private static final int METHODID_ADD_TO_CART = 1;
  private static final int METHODID_REMOVE_FROM_CART = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CartServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CartServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CART_INFO:
          serviceImpl.getCartInfo((com.example.shopping.api.Shopping.GetCartInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.GetCartInfoResponse>) responseObserver);
          break;
        case METHODID_ADD_TO_CART:
          serviceImpl.addToCart((com.example.shopping.api.Shopping.AddToCartRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.AddToCartResponse>) responseObserver);
          break;
        case METHODID_REMOVE_FROM_CART:
          serviceImpl.removeFromCart((com.example.shopping.api.Shopping.RemoveFromCartRequest) request,
              (io.grpc.stub.StreamObserver<com.example.shopping.api.Shopping.RemoveFromCartResponse>) responseObserver);
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

  private static final class CartServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.shopping.api.Shopping.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CartServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CartServiceDescriptorSupplier())
              .addMethod(METHOD_GET_CART_INFO)
              .addMethod(METHOD_ADD_TO_CART)
              .addMethod(METHOD_REMOVE_FROM_CART)
              .build();
        }
      }
    }
    return result;
  }
}
