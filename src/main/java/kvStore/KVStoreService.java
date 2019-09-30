package kvStore;

import DB.KeyValueDatabase;
import com.grpc.sample.KvStore;
import com.grpc.sample.kvStoreGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KVStoreService extends kvStoreGrpc.kvStoreImplBase {

    HashMap<String, String> kvMap;
    HashSet<Integer> deadSet;
    Integer updateNode;
    Long updateNodeTimestamp;
    Integer rank;
    Integer n;
    KeyValueDatabase keyValueDatabase;
    HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap;
    public KVStoreService() {
        this.kvMap = new HashMap<>();
        this.deadSet = new HashSet<>();
        this.keyValueDatabase = new KeyValueDatabase(rank);
    }

    public KVStoreService(Integer rank, Integer n) {
        this.kvMap = new HashMap<>();
        this.rank = rank;
        this.n = n;
        this.keyValueDatabase = new KeyValueDatabase(rank);
        this.deadSet = new HashSet<>();
    }

    public void setUpdateNodeTimestamp(Long updateNodeTimestamp) {
        this.updateNodeTimestamp = updateNodeTimestamp;
    }

    public void setUpdateNode(Integer updateNode) {
        this.updateNode = updateNode;
    }

    public void setStubHashMap(HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap) {
        this.stubHashMap = stubHashMap;
    }

    public Pair<Integer, Long> fetchUpdateNodeInfoFromDB() {
        return keyValueDatabase.getUpdateNodeInfo();
    }

    public void setUpdateNodeAndTimestampDB(int updateNode, long updateNodeTimestamp) {
        keyValueDatabase.updateUpdateNodeTable(updateNode, updateNodeTimestamp);
        this.updateNode = updateNode;
        this.updateNodeTimestamp = updateNodeTimestamp;
    }

    public void reconciliateDB(List<KvStore.Row> records) {
        keyValueDatabase.reconciliateDB(records);
    }

    public void refreshHashTable() {
        List<KvStore.Row> records = keyValueDatabase.getRecordsSince(1);
//        HashMap<String, String> refreshHashMap = new HashMap<>();
        for (KvStore.Row record : records) {
            this.kvMap.put(record.getKey(), record.getValue());
        }
    }

    @Override
    public void get(KvStore.GetRequest request, StreamObserver<KvStore.GetResponse> responseObserver) {

        System.out.println("Server: " + rank + " GET");
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

    public boolean validateInput(String key, String value) {
        if(key.length() > 128 || value.length() > 2048)
            return false;

        String regex = "[^a-zA-Z0-9]";
        Pattern keyPattern = Pattern.compile(regex);
        Matcher KeyMatcher = keyPattern.matcher(key);
        boolean keyValidation = KeyMatcher.find();
        
        Pattern valuePattern = Pattern.compile(regex);
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean valueValidation = valueMatcher.find();

        return !keyValidation && !valueValidation;
    }


    @Override
    public void syncMessage(KvStore.SyncMessageRequest request, StreamObserver<KvStore.SyncMessageResponse> responseObserver) {
        long timestamp = request.getSyncTimestamp();
        List<KvStore.Row> records = keyValueDatabase.getRecordsSince(timestamp);
        KvStore.SyncMessageResponse.Builder response = KvStore.SyncMessageResponse.newBuilder();
        response.addAllRow(records);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public long getMaxTimestamp() {
        return keyValueDatabase.getMaxTimestampFromKVTable();
    }

    @Override
    public void getUpdateNode(KvStore.UpdateNodeRequest request, StreamObserver<KvStore.UpdateNodeResponse> responseObserver) {

        deadSet.remove(request.getSourceNode());
        KvStore.UpdateNodeResponse.Builder response = KvStore.UpdateNodeResponse.newBuilder();
        response.setUpdateNode(updateNode);
        response.setUpdateNodeTimestamp(updateNodeTimestamp);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void put(KvStore.PutRequest request, StreamObserver<KvStore.PutResponse> responseObserver) {
        System.out.println("Server: " + rank + " PUT");
        String key = request.getRequestKey();
        String newValue = request.getRequestNewValue();
        boolean validate = validateInput(key, newValue);
        if(!validate) {
            KvStore.PutResponse.Builder putResponseBuilder = KvStore.PutResponse.newBuilder();
            putResponseBuilder.setStatus(-1);
            responseObserver.onNext(putResponseBuilder.build());
            responseObserver.onCompleted();
            return;
        }
        boolean isFailed = false;

        if (request.getUpdateNode() == -1) {
            KvStore.PutRequest.Builder putRequestBuilder = KvStore.PutRequest.newBuilder();
            putRequestBuilder.setRequestKey(key);
            putRequestBuilder.setRequestOldValue(request.getRequestOldValue());
            putRequestBuilder.setRequestNewValue(newValue);
            putRequestBuilder.setUpdateNode(updateNode);
            //TODO: add timestamp
            request = putRequestBuilder.build();
        }

        //TODO: Need to investigate if timestamp check is necessary
        if (request.getUpdateNode() != -1 && request.getUpdateNode() != updateNode) {
            updateNode = request.getUpdateNode();
            deadSet = new HashSet<>(request.getDeadRanksList());
            updateNodeTimestamp = request.getUpdateNodeTimestamp();
            //Save new update node in DB
            keyValueDatabase.updateUpdateNodeTable(updateNode, updateNodeTimestamp);
        }

        KvStore.PutResponse putResponse = null;
        if (updateNode != rank) {
            kvStoreGrpc.kvStoreBlockingStub kvStub = stubHashMap.get(updateNode);
            while (updateNode != rank) {
                try {
                    System.out.println("Server: " + rank + " Calling update node: " + updateNode);
                    putResponse = kvStub.withDeadlineAfter(12000, TimeUnit.MILLISECONDS).put(request);

                    break;
                } catch (StatusRuntimeException timeoutException) {
                    System.out.println("Server: " + rank + " Timeout exception - catch block" + timeoutException.getStatus());
                    //Add failed node to deadset
                    deadSet.add(updateNode);
                    isFailed = true;
                    // Update the update node
                    updateNode = (updateNode + 1) % n;
                    updateNodeTimestamp = System.currentTimeMillis();
                    System.out.println("Server: " + rank + " Updated value of update node: " + updateNode);
                    kvStub = stubHashMap.get(updateNode);

                    KvStore.PutRequest.Builder putRequestBuilder = KvStore.PutRequest.newBuilder();
                    putRequestBuilder.setRequestKey(key);
                    putRequestBuilder.setRequestOldValue(request.getRequestOldValue());
                    putRequestBuilder.setRequestNewValue(newValue);
                    putRequestBuilder.setUpdateNode(updateNode);
                    putRequestBuilder.setUpdateNodeTimestamp(updateNodeTimestamp);
                    putRequestBuilder.addAllDeadRanks(deadSet);
                    request = putRequestBuilder.build();

                }
            }
            if (isFailed) {
                keyValueDatabase.updateUpdateNodeTable(updateNode, updateNodeTimestamp);
            }

//            //new rpc Broadcast
//            for (int i = 0; i < n; i++) {
//                if (i != rank) {
//                    kvStoreGrpc.kvStoreBlockingStub kvBroadcastStub = stubHashMap.get(i);
//                    KvStore.UpdateNodePushMessage.Builder updateNodePushMessageBuilder = KvStore.UpdateNodePushMessage.newBuilder();
//                    updateNodePushMessageBuilder.setNewUpdateNode(updateNode);
//                    updateNodePushMessageBuilder.setNewUpdateNodeTimestamp(System.currentTimeMillis());
//                    kvBroadcastStub.updateNodeInfo(updateNodePushMessageBuilder.build());
//                }
//            }
            if (updateNode == rank) {
                putResponse = updateNodeOperations(key, newValue);
            }
        } else {
            putResponse = updateNodeOperations(key, newValue);
        }
        // note: updating only hashmap and not db - waiting for broadcast msg to update db
        kvMap.put(key, putResponse.getResponseNewValue());

        responseObserver.onNext(putResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void pushMessage(KvStore.PushMessageRequest request, StreamObserver<KvStore.PushMessageResponse> responseObserver) {
        System.out.println("Server: " + rank + " PushMessage");
        String key = request.getPushKey();
        String newValue = request.getPushValue();
        long timestamp = request.getPushTimestamp();

        if (request.getUpdateNode() != updateNode) {
            updateNode = request.getUpdateNode();
            updateNodeTimestamp = request.getUpdateNodeTimestamp();
            //Save new updateNode value in DB
            keyValueDatabase.updateUpdateNodeTable(updateNode, updateNodeTimestamp);

        }

        deadSet = new HashSet<>(request.getDeadRanksList());
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


    public KvStore.PutResponse updateNodeOperations(String key, String newValue) {
        Long time = null;
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
        KvStore.PutResponse putResponse = putResponseBuilder.build();
        System.out.println("Server: " + rank + "Printing deadset: " + deadSet);
        //Broadcast
        for (int i = 0; i < n; i++) {
            if (i != rank && !deadSet.contains(i)) {
                System.out.println("Server: " + rank + " broadcast to: " + i);
                //TODO: try catch for handling failed broadcast
                kvStoreGrpc.kvStoreBlockingStub kvStub = stubHashMap.get(i);
                KvStore.PushMessageRequest.Builder pushMessageRequestBuilder = KvStore.PushMessageRequest.newBuilder();
                pushMessageRequestBuilder.setPushKey(key);
                pushMessageRequestBuilder.setPushValue(newValue);
                //TODO :  check for long value
                pushMessageRequestBuilder.setPushTimestamp(time.intValue());
                pushMessageRequestBuilder.setUpdateNodeTimestamp(updateNodeTimestamp);
                pushMessageRequestBuilder.setUpdateNode(updateNode);
                pushMessageRequestBuilder.addAllDeadRanks(deadSet);


                //TODO : update node timestamp
                try {
                    kvStub.withDeadlineAfter(1000, TimeUnit.MILLISECONDS).pushMessage(pushMessageRequestBuilder.build());
                    System.out.println("DONE!! - Server: " + rank + " broadcast to: " + i);
                } catch (StatusRuntimeException timeoutException) {

                    deadSet.add(i);
                    System.out.println("Server: " + rank + ": failed to contact: " + i);
                    System.out.println("Exception is : " + timeoutException.getMessage());
                }
            }
        }
        return putResponse;
    }
}
