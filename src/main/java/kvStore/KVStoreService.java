package kvStore;

import DB.KeyValueDatabase;
import com.grpc.sample.KvStore;
import com.grpc.sample.kvStoreGrpc;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;

public class KVStoreService extends kvStoreGrpc.kvStoreImplBase {

    HashMap<String, String> kvMap;
    Integer updateNode;
    Integer rank;
    Integer n;
    KeyValueDatabase keyValueDatabase;
    HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap;
    public KVStoreService() {
        this.kvMap = new HashMap<>();
        this.keyValueDatabase = new KeyValueDatabase(rank);
    }

    public KVStoreService(Integer updateNode, Integer rank, Integer n) {
        this.kvMap = new HashMap<>();
        this.updateNode = updateNode;
        this.rank = rank;
        this.n = n;
        this.keyValueDatabase = new KeyValueDatabase(rank);
    }

    public void setStubHashMap(HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap) {
        this.stubHashMap = stubHashMap;
    }

    @Override
    public void get(KvStore.GetRequest request, StreamObserver<KvStore.GetResponse> responseObserver) {

        System.out.println("inside get of : " + rank);
        String key = request.getRequestKey();
        String value = request.getRequestValue();
        KvStore.GetResponse.Builder getResponse = KvStore.GetResponse.newBuilder();
        getResponse.setResponseKey(key);
        if (kvMap.containsKey(key)) {
            getResponse.setResponseValue(kvMap.get(key));
            getResponse.setStatus(0);
        } else {
            getResponse.setResponseValue(value);
            getResponse.setStatus(1);
        }
        responseObserver.onNext(getResponse.build());
        responseObserver.onCompleted();

    }

    @Override
    public void put(KvStore.PutRequest request, StreamObserver<KvStore.PutResponse> responseObserver) {
        System.out.println("inside put of : " + rank);
        String key = request.getRequestKey();
        String newValue = request.getRequestNewValue();
        Long time = null;

        KvStore.PutResponse putResponse = null;
        if (updateNode != rank) {
            kvStoreGrpc.kvStoreBlockingStub kvStub = stubHashMap.get(updateNode);
            putResponse = kvStub.put(request);
            // note: updating only hashmap and not db - waiting for broadcast msg to update db
            kvMap.put(key, putResponse.getResponseNewValue());
        } else {
            //HashTable response
            KvStore.PutResponse.Builder putResponseBuilder = KvStore.PutResponse.newBuilder();
            putResponseBuilder.setResponseKey(key);
            String oldValue = kvMap.put(key, newValue);
            if (oldValue != null)
                putResponseBuilder.setResponseOldValue(oldValue);
            putResponseBuilder.setResponseNewValue(newValue);
            putResponseBuilder.setStatus(oldValue == null ? 1 : 0);

            //Update DB
            time = System.currentTimeMillis();
            if (oldValue == null) {
                keyValueDatabase.insertIntoTable(key, newValue, time);
            } else {
                keyValueDatabase.updateKeyIfExistsInTable(key, newValue, time);
            }
            putResponse = putResponseBuilder.build();

            //Broadcast
            for (int i = 0; i < n; i++) {
                if (i != rank) {
                    kvStoreGrpc.kvStoreBlockingStub kvStub = stubHashMap.get(i);
                    KvStore.PushMessageRequest.Builder pushMessageRequestBuilder = KvStore.PushMessageRequest.newBuilder();
                    pushMessageRequestBuilder.setPushKey(key);
                    pushMessageRequestBuilder.setPushValue(newValue);
                    //TODO :  check for long value
                    pushMessageRequestBuilder.setPushTimestamp(time.intValue());
                    kvStub.pushMessage(pushMessageRequestBuilder.build());
                }
            }
        }
        responseObserver.onNext(putResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void pushMessage(KvStore.PushMessageRequest request, StreamObserver<KvStore.PushMessageResponse> responseObserver) {
        System.out.println("inside pushMessage of: " + rank);
        String key = request.getPushKey();
        String newValue = request.getPushValue();
        long timestamp = request.getPushTimestamp();
        KvStore.PushMessageResponse.Builder pushMessageResponseBuilder = KvStore.PushMessageResponse.newBuilder();
        String oldValue = kvMap.put(key, newValue);

        pushMessageResponseBuilder.setPushResponseKey(key);
        pushMessageResponseBuilder.setPushResponseValue(newValue);
        pushMessageResponseBuilder.setPushResponseTimestamp(timestamp);

        //Update DB
        if (oldValue == null) {
            keyValueDatabase.insertIntoTable(key, newValue, timestamp);
        } else {
            keyValueDatabase.updateKeyIfExistsInTable(key, newValue, timestamp);
        }
        responseObserver.onNext(pushMessageResponseBuilder.build());
        responseObserver.onCompleted();
    }
}
