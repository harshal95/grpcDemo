package kvStore;

import com.grpc.sample.DynamoStore;
import com.grpc.sample.dynamoStoreGrpc;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class DynamoStoreService extends dynamoStoreGrpc.dynamoStoreImplBase {
    Integer rank;
    Integer n;
    HashMap<String, String> dynamoMap;
    HashMap<Integer, dynamoStoreGrpc.dynamoStoreStub> stubHashMap;

    public DynamoStoreService(Integer rank, Integer n) {
        this.rank = rank;
        this.n = n;
        this.dynamoMap =  new HashMap<>();
    }


    public void setStubHashMap(HashMap<Integer, dynamoStoreGrpc.dynamoStoreStub> stubHashMap) {
        this.stubHashMap = stubHashMap;
    }

    @Override
    public void get(DynamoStore.GetRequest request, StreamObserver<DynamoStore.GetResponse> responseObserver) {
        super.get(request, responseObserver);
    }

    @Override
    public void put(DynamoStore.PutRequest request, StreamObserver<DynamoStore.PutResponse> responseObserver){

        System.out.println("Server: " + rank + " PUT");
        String key = request.getRequestKey();
        String newValue = request.getRequestNewValue();

        dynamoMap.put(key, newValue);
        System.out.println("Key: " + key + " Value: " + newValue);

        DynamoStore.PropagateRequest.Builder propagateRequestBuilder = DynamoStore.PropagateRequest.newBuilder();
        propagateRequestBuilder.setRequestKey(key);
        propagateRequestBuilder.setRequestNewValue(newValue);

        CountDownLatch finishLatch = new CountDownLatch(n-1);

        for(int i=0; i<n; i++) {
            if(rank != i) {
                dynamoStoreGrpc.dynamoStoreStub dynamoStoreStub = stubHashMap.get(i);
                dynamoStoreStub.propagate(propagateRequestBuilder.build(), new StreamObserver<DynamoStore.PropagateResponse>() {
                    @Override
                    public void onNext(DynamoStore.PropagateResponse response) {
                        System.out.println("Inside onNext() func");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Inside onError() func: " + throwable.getMessage());
                        finishLatch.countDown();
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("Server: " + rank + "Inside onCompleted() func");
                        finishLatch.countDown();
                    }

                });
            }
        }

        DynamoStore.PutResponse.Builder putResponseBuilder = DynamoStore.PutResponse.newBuilder();
        putResponseBuilder.setStatus(1);
        putResponseBuilder.setResponseKey(key);
        putResponseBuilder.setResponseNewValue(newValue);


        responseObserver.onNext(putResponseBuilder.build());
        responseObserver.onCompleted();
        System.out.println("Server: " + rank + "Finished put");
        try {
            finishLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void propagate(DynamoStore.PropagateRequest request, StreamObserver<DynamoStore.PropagateResponse> responseObserver) {
            System.out.println("Server: " + rank + "Inside propagate func");
            dynamoMap.put(request.getRequestKey(), request.getRequestNewValue());
            System.out.println("Replicated Key: " + request.getRequestKey() + " Value: " + dynamoMap.get(request.getRequestKey()));
            DynamoStore.PropagateResponse.Builder propagateResponseBuilder = DynamoStore.PropagateResponse.newBuilder();
            propagateResponseBuilder.setResponseKey(request.getRequestKey());
            propagateResponseBuilder.setResponseNewValue(request.getRequestNewValue());
            propagateResponseBuilder.setStatus(1);

            responseObserver.onNext(propagateResponseBuilder.build());
            responseObserver.onCompleted();
    }
}
