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
    comments = "Source: kvStore.proto")
public final class kvStoreGrpc {

  private kvStoreGrpc() {}

  public static final String SERVICE_NAME = "kvStore";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.KvStore.GetRequest,
      com.grpc.sample.KvStore.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = com.grpc.sample.KvStore.GetRequest.class,
      responseType = com.grpc.sample.KvStore.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.KvStore.GetRequest,
      com.grpc.sample.KvStore.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.KvStore.GetRequest, com.grpc.sample.KvStore.GetResponse> getGetMethod;
    if ((getGetMethod = kvStoreGrpc.getGetMethod) == null) {
      synchronized (kvStoreGrpc.class) {
        if ((getGetMethod = kvStoreGrpc.getGetMethod) == null) {
          kvStoreGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.KvStore.GetRequest, com.grpc.sample.KvStore.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "kvStore", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.GetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new kvStoreMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PutRequest,
      com.grpc.sample.KvStore.PutResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = com.grpc.sample.KvStore.PutRequest.class,
      responseType = com.grpc.sample.KvStore.PutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PutRequest,
      com.grpc.sample.KvStore.PutResponse> getPutMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PutRequest, com.grpc.sample.KvStore.PutResponse> getPutMethod;
    if ((getPutMethod = kvStoreGrpc.getPutMethod) == null) {
      synchronized (kvStoreGrpc.class) {
        if ((getPutMethod = kvStoreGrpc.getPutMethod) == null) {
          kvStoreGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.KvStore.PutRequest, com.grpc.sample.KvStore.PutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "kvStore", "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.PutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new kvStoreMethodDescriptorSupplier("put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PushMessageRequest,
      com.grpc.sample.KvStore.PushMessageResponse> getPushMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pushMessage",
      requestType = com.grpc.sample.KvStore.PushMessageRequest.class,
      responseType = com.grpc.sample.KvStore.PushMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PushMessageRequest,
      com.grpc.sample.KvStore.PushMessageResponse> getPushMessageMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.KvStore.PushMessageRequest, com.grpc.sample.KvStore.PushMessageResponse> getPushMessageMethod;
    if ((getPushMessageMethod = kvStoreGrpc.getPushMessageMethod) == null) {
      synchronized (kvStoreGrpc.class) {
        if ((getPushMessageMethod = kvStoreGrpc.getPushMessageMethod) == null) {
          kvStoreGrpc.getPushMessageMethod = getPushMessageMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.KvStore.PushMessageRequest, com.grpc.sample.KvStore.PushMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "kvStore", "pushMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.PushMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.PushMessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new kvStoreMethodDescriptorSupplier("pushMessage"))
                  .build();
          }
        }
     }
     return getPushMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.KvStore.UpdateNodeRequest,
      com.grpc.sample.KvStore.UpdateNodeResponse> getGetUpdateNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUpdateNode",
      requestType = com.grpc.sample.KvStore.UpdateNodeRequest.class,
      responseType = com.grpc.sample.KvStore.UpdateNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.KvStore.UpdateNodeRequest,
      com.grpc.sample.KvStore.UpdateNodeResponse> getGetUpdateNodeMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.KvStore.UpdateNodeRequest, com.grpc.sample.KvStore.UpdateNodeResponse> getGetUpdateNodeMethod;
    if ((getGetUpdateNodeMethod = kvStoreGrpc.getGetUpdateNodeMethod) == null) {
      synchronized (kvStoreGrpc.class) {
        if ((getGetUpdateNodeMethod = kvStoreGrpc.getGetUpdateNodeMethod) == null) {
          kvStoreGrpc.getGetUpdateNodeMethod = getGetUpdateNodeMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.KvStore.UpdateNodeRequest, com.grpc.sample.KvStore.UpdateNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "kvStore", "getUpdateNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.UpdateNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.UpdateNodeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new kvStoreMethodDescriptorSupplier("getUpdateNode"))
                  .build();
          }
        }
     }
     return getGetUpdateNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.sample.KvStore.SyncMessageRequest,
      com.grpc.sample.KvStore.SyncMessageResponse> getSyncMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "syncMessage",
      requestType = com.grpc.sample.KvStore.SyncMessageRequest.class,
      responseType = com.grpc.sample.KvStore.SyncMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.sample.KvStore.SyncMessageRequest,
      com.grpc.sample.KvStore.SyncMessageResponse> getSyncMessageMethod() {
    io.grpc.MethodDescriptor<com.grpc.sample.KvStore.SyncMessageRequest, com.grpc.sample.KvStore.SyncMessageResponse> getSyncMessageMethod;
    if ((getSyncMessageMethod = kvStoreGrpc.getSyncMessageMethod) == null) {
      synchronized (kvStoreGrpc.class) {
        if ((getSyncMessageMethod = kvStoreGrpc.getSyncMessageMethod) == null) {
          kvStoreGrpc.getSyncMessageMethod = getSyncMessageMethod = 
              io.grpc.MethodDescriptor.<com.grpc.sample.KvStore.SyncMessageRequest, com.grpc.sample.KvStore.SyncMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "kvStore", "syncMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.SyncMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.sample.KvStore.SyncMessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new kvStoreMethodDescriptorSupplier("syncMessage"))
                  .build();
          }
        }
     }
     return getSyncMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static kvStoreStub newStub(io.grpc.Channel channel) {
    return new kvStoreStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static kvStoreBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new kvStoreBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static kvStoreFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new kvStoreFutureStub(channel);
  }

  /**
   */
  public static abstract class kvStoreImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.grpc.sample.KvStore.GetRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.GetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(com.grpc.sample.KvStore.PutRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void pushMessage(com.grpc.sample.KvStore.PushMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PushMessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPushMessageMethod(), responseObserver);
    }

    /**
     */
    public void getUpdateNode(com.grpc.sample.KvStore.UpdateNodeRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.UpdateNodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUpdateNodeMethod(), responseObserver);
    }

    /**
     */
    public void syncMessage(com.grpc.sample.KvStore.SyncMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.SyncMessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSyncMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.KvStore.GetRequest,
                com.grpc.sample.KvStore.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.KvStore.PutRequest,
                com.grpc.sample.KvStore.PutResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getPushMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.KvStore.PushMessageRequest,
                com.grpc.sample.KvStore.PushMessageResponse>(
                  this, METHODID_PUSH_MESSAGE)))
          .addMethod(
            getGetUpdateNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.KvStore.UpdateNodeRequest,
                com.grpc.sample.KvStore.UpdateNodeResponse>(
                  this, METHODID_GET_UPDATE_NODE)))
          .addMethod(
            getSyncMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.sample.KvStore.SyncMessageRequest,
                com.grpc.sample.KvStore.SyncMessageResponse>(
                  this, METHODID_SYNC_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class kvStoreStub extends io.grpc.stub.AbstractStub<kvStoreStub> {
    private kvStoreStub(io.grpc.Channel channel) {
      super(channel);
    }

    private kvStoreStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected kvStoreStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new kvStoreStub(channel, callOptions);
    }

    /**
     */
    public void get(com.grpc.sample.KvStore.GetRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.GetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(com.grpc.sample.KvStore.PutRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pushMessage(com.grpc.sample.KvStore.PushMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PushMessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPushMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUpdateNode(com.grpc.sample.KvStore.UpdateNodeRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.UpdateNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUpdateNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void syncMessage(com.grpc.sample.KvStore.SyncMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.SyncMessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSyncMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class kvStoreBlockingStub extends io.grpc.stub.AbstractStub<kvStoreBlockingStub> {
    private kvStoreBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private kvStoreBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected kvStoreBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new kvStoreBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.grpc.sample.KvStore.GetResponse get(com.grpc.sample.KvStore.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.KvStore.PutResponse put(com.grpc.sample.KvStore.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.KvStore.PushMessageResponse pushMessage(com.grpc.sample.KvStore.PushMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getPushMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.KvStore.UpdateNodeResponse getUpdateNode(com.grpc.sample.KvStore.UpdateNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUpdateNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.sample.KvStore.SyncMessageResponse syncMessage(com.grpc.sample.KvStore.SyncMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSyncMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class kvStoreFutureStub extends io.grpc.stub.AbstractStub<kvStoreFutureStub> {
    private kvStoreFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private kvStoreFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected kvStoreFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new kvStoreFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.KvStore.GetResponse> get(
        com.grpc.sample.KvStore.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.KvStore.PutResponse> put(
        com.grpc.sample.KvStore.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.KvStore.PushMessageResponse> pushMessage(
        com.grpc.sample.KvStore.PushMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPushMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.KvStore.UpdateNodeResponse> getUpdateNode(
        com.grpc.sample.KvStore.UpdateNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUpdateNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.sample.KvStore.SyncMessageResponse> syncMessage(
        com.grpc.sample.KvStore.SyncMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSyncMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_PUT = 1;
  private static final int METHODID_PUSH_MESSAGE = 2;
  private static final int METHODID_GET_UPDATE_NODE = 3;
  private static final int METHODID_SYNC_MESSAGE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final kvStoreImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(kvStoreImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.grpc.sample.KvStore.GetRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.GetResponse>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((com.grpc.sample.KvStore.PutRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PutResponse>) responseObserver);
          break;
        case METHODID_PUSH_MESSAGE:
          serviceImpl.pushMessage((com.grpc.sample.KvStore.PushMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.PushMessageResponse>) responseObserver);
          break;
        case METHODID_GET_UPDATE_NODE:
          serviceImpl.getUpdateNode((com.grpc.sample.KvStore.UpdateNodeRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.UpdateNodeResponse>) responseObserver);
          break;
        case METHODID_SYNC_MESSAGE:
          serviceImpl.syncMessage((com.grpc.sample.KvStore.SyncMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.sample.KvStore.SyncMessageResponse>) responseObserver);
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

  private static abstract class kvStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    kvStoreBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.sample.KvStore.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("kvStore");
    }
  }

  private static final class kvStoreFileDescriptorSupplier
      extends kvStoreBaseDescriptorSupplier {
    kvStoreFileDescriptorSupplier() {}
  }

  private static final class kvStoreMethodDescriptorSupplier
      extends kvStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    kvStoreMethodDescriptorSupplier(String methodName) {
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
      synchronized (kvStoreGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new kvStoreFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getPushMessageMethod())
              .addMethod(getGetUpdateNodeMethod())
              .addMethod(getSyncMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
