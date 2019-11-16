package com.grpc.sample;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: dynamoStore.proto")
public final class dynamoStoreGrpc {

  private dynamoStoreGrpc() {}

  public static final String SERVICE_NAME = "dynamoStore";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.GetRequest,
      com.grpc.sample.DynamoStore.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = com.grpc.sample.DynamoStore.GetRequest.class,
      responseType = com.grpc.sample.DynamoStore.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.GetRequest,
      com.grpc.sample.DynamoStore.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.GetRequest, com.grpc.sample.DynamoStore.GetResponse> getGetMethod;
    if ((getGetMethod = dynamoStoreGrpc.getGetMethod) == null) {
      synchronized (dynamoStoreGrpc.class) {
        if ((getGetMethod = dynamoStoreGrpc.getGetMethod) == null) {
          dynamoStoreGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.DynamoStore.GetRequest, com.grpc.sample.DynamoStore.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dynamoStore", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.GetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new dynamoStoreMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PutRequest,
      com.grpc.sample.DynamoStore.PutResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = com.grpc.sample.DynamoStore.PutRequest.class,
      responseType = com.grpc.sample.DynamoStore.PutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PutRequest,
      com.grpc.sample.DynamoStore.PutResponse> getPutMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PutRequest, com.grpc.sample.DynamoStore.PutResponse> getPutMethod;
    if ((getPutMethod = dynamoStoreGrpc.getPutMethod) == null) {
      synchronized (dynamoStoreGrpc.class) {
        if ((getPutMethod = dynamoStoreGrpc.getPutMethod) == null) {
          dynamoStoreGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.DynamoStore.PutRequest, com.grpc.sample.DynamoStore.PutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dynamoStore", "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.PutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new dynamoStoreMethodDescriptorSupplier("put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PropagateRequest,
      com.grpc.sample.DynamoStore.PropagateResponse> getPropagateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "propagate",
      requestType = com.grpc.sample.DynamoStore.PropagateRequest.class,
      responseType = com.grpc.sample.DynamoStore.PropagateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PropagateRequest,
      com.grpc.sample.DynamoStore.PropagateResponse> getPropagateMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.DynamoStore.PropagateRequest, com.grpc.sample.DynamoStore.PropagateResponse> getPropagateMethod;
    if ((getPropagateMethod = dynamoStoreGrpc.getPropagateMethod) == null) {
      synchronized (dynamoStoreGrpc.class) {
        if ((getPropagateMethod = dynamoStoreGrpc.getPropagateMethod) == null) {
          dynamoStoreGrpc.getPropagateMethod = getPropagateMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.DynamoStore.PropagateRequest, com.grpc.sample.DynamoStore.PropagateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dynamoStore", "propagate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.PropagateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.DynamoStore.PropagateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new dynamoStoreMethodDescriptorSupplier("propagate"))
                  .build();
          }
        }
     }
     return getPropagateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static dynamoStoreStub newStub(io.grpc.Channel channel) {
    return new dynamoStoreStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static dynamoStoreBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new dynamoStoreBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static dynamoStoreFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new dynamoStoreFutureStub(channel);
  }

  /**
   */
  public static abstract class dynamoStoreImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.grpc.sample.DynamoStore.GetRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.GetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(com.grpc.sample.DynamoStore.PutRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void propagate(com.grpc.sample.DynamoStore.PropagateRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PropagateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPropagateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.DynamoStore.GetRequest,
                com.grpc.sample.DynamoStore.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.DynamoStore.PutRequest,
                com.grpc.sample.DynamoStore.PutResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getPropagateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.DynamoStore.PropagateRequest,
                com.grpc.sample.DynamoStore.PropagateResponse>(
                  this, METHODID_PROPAGATE)))
          .build();
    }
  }

  /**
   */
  public static final class dynamoStoreStub extends io.grpc.stub.AbstractStub<dynamoStoreStub> {
    private dynamoStoreStub(io.grpc.Channel channel) {
      super(channel);
    }

    private dynamoStoreStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected dynamoStoreStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new dynamoStoreStub(channel, callOptions);
    }

    /**
     */
    public void get(com.grpc.sample.DynamoStore.GetRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.GetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(com.grpc.sample.DynamoStore.PutRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void propagate(com.grpc.sample.DynamoStore.PropagateRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PropagateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPropagateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class dynamoStoreBlockingStub extends io.grpc.stub.AbstractStub<dynamoStoreBlockingStub> {
    private dynamoStoreBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private dynamoStoreBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected dynamoStoreBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new dynamoStoreBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.grpc.sample.DynamoStore.GetResponse get(com.grpc.sample.DynamoStore.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.DynamoStore.PutResponse put(com.grpc.sample.DynamoStore.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.DynamoStore.PropagateResponse propagate(com.grpc.sample.DynamoStore.PropagateRequest request) {
      return blockingUnaryCall(
          getChannel(), getPropagateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class dynamoStoreFutureStub extends io.grpc.stub.AbstractStub<dynamoStoreFutureStub> {
    private dynamoStoreFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private dynamoStoreFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected dynamoStoreFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new dynamoStoreFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.DynamoStore.GetResponse> get(
        com.grpc.sample.DynamoStore.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.DynamoStore.PutResponse> put(
        com.grpc.sample.DynamoStore.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.DynamoStore.PropagateResponse> propagate(
        com.grpc.sample.DynamoStore.PropagateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPropagateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_PUT = 1;
  private static final int METHODID_PROPAGATE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final dynamoStoreImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(dynamoStoreImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.grpc.sample.DynamoStore.GetRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.GetResponse>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((com.grpc.sample.DynamoStore.PutRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PutResponse>) responseObserver);
          break;
        case METHODID_PROPAGATE:
          serviceImpl.propagate((com.grpc.sample.DynamoStore.PropagateRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.DynamoStore.PropagateResponse>) responseObserver);
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

  private static abstract class dynamoStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    dynamoStoreBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.sample.DynamoStore.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("dynamoStore");
    }
  }

  private static final class dynamoStoreFileDescriptorSupplier
      extends dynamoStoreBaseDescriptorSupplier {
    dynamoStoreFileDescriptorSupplier() {}
  }

  private static final class dynamoStoreMethodDescriptorSupplier
      extends dynamoStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    dynamoStoreMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (dynamoStoreGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new dynamoStoreFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getPropagateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
