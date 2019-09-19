package kvStore;

import com.grpc.sample.KvStore;
import com.grpc.sample.kvStoreGrpc;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;

public class KVStoreService extends kvStoreGrpc.kvStoreImplBase {

    HashMap<String, String> kvMap;

    public KVStoreService() {
        kvMap = new HashMap<>();
//        kvMap.put("drink", "tea");
//        kvMap.put("eat", "biryani");
    }

    @Override
    public void get(KvStore.GetRequest request, StreamObserver<KvStore.GetResponse> responseObserver) {

        System.out.println("inside get");
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
//        super.put(request, responseObserver);
        System.out.println("inside put");
        String key = request.getRequestKey();
        String newValue = request.getRequestNewValue();

        KvStore.PutResponse.Builder putResponse = KvStore.PutResponse.newBuilder();
        putResponse.setResponseKey(key);
        String oldValue = kvMap.put(key, newValue);
        if (oldValue != null)
            putResponse.setResponseOldValue(oldValue);
        putResponse.setResponseNewValue(newValue);
        putResponse.setStatus(oldValue == null ? 0 : 1);

        responseObserver.onNext(putResponse.build());
        responseObserver.onCompleted();
    }
}
