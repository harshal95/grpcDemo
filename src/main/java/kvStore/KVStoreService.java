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
        if (updateNode == rank) {
            keyValueDatabase.insertIntoTable(key, newValue, System.currentTimeMillis());
        }
        responseObserver.onNext(putResponse.build());
        responseObserver.onCompleted();
    }
}
