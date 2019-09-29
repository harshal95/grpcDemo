package grpcserver;

import com.grpc.sample.KvStore;
import com.grpc.sample.kvStoreGrpc;
import io.grpc.*;
import javafx.util.Pair;
import kvStore.KVStoreService;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GRPCServer {

    public static void main(String []args) throws IOException, InterruptedException {
        int n = Integer.parseInt(args[0]);
        int rank = Integer.parseInt(args[1]);
        int updateNode = Integer.parseInt(args[2]);
        int port = Integer.parseInt(args[3]);
        System.out.println("In GRPC Server: n=" + n + " rank=" + rank + " port=" + port);
        if(args.length < 4) {
            System.out.println("Invalid Arguments passed - GO BACK and CHECK");
            return;
        }

        HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap = new HashMap<>();

        KVStoreService kvStoreService = new KVStoreService(rank, n);
        Pair<Integer, Long> updateNodeInfo = kvStoreService.fetchUpdateNodeInfoFromDB();
        kvStoreService.setUpdateNode(updateNodeInfo.getKey());
        kvStoreService.setUpdateNodeTimestamp(updateNodeInfo.getValue());

        Server server = ServerBuilder.forPort(port).addService(kvStoreService).build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        System.out.println("Server: " + rank + " Pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int basePort = port - rank;
        for(int i = 0; i < n; i++) {
            if(i != rank) {
                //System.out.println("Server: " + rank + "Connecting to stub of: " + i);
                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", basePort + i).usePlaintext().build();
                kvStoreGrpc.kvStoreBlockingStub kvStub = kvStoreGrpc.newBlockingStub(managedChannel).withWaitForReady();
                stubHashMap.put(i, kvStub);
            }
        }
        System.out.println("Server: " + rank + " Stub creation done");
        kvStoreService.setStubHashMap(stubHashMap);
        int resUpdateNode = updateNodeInfo.getKey();
        long resTimestamp = updateNodeInfo.getValue();

        //TODO: handle fault : Send update node I have to all others
        int i = 0;
        while(i < n) {
            System.out.println("GRPC Server Main:" + rank + " Inside While of send update node to all ");
            if (i != rank) {
                System.out.println("GRPC Server Main: " + rank + "Inside IF Sending to: " + i);
                kvStoreGrpc.kvStoreBlockingStub kvGetUpdateStub = stubHashMap.get(i);
                KvStore.UpdateNodeRequest.Builder requestBuilder = KvStore.UpdateNodeRequest.newBuilder();
                requestBuilder.setUpdateNode(updateNodeInfo.getKey());
                requestBuilder.setUpdateNodeTimestamp(updateNodeInfo.getValue());
                requestBuilder.setSourceNode(rank);
                try {
                    System.out.println("GRPC Server Main: " + rank + "Inside TRY Sending to: " + i);
                    KvStore.UpdateNodeResponse response = kvGetUpdateStub.withDeadlineAfter(5, TimeUnit.SECONDS).getUpdateNode(requestBuilder.build());
                    System.out.println("Server : " + rank + " Sent update node request to: " + i);
                    if (response.getUpdateNode() != resUpdateNode
                            && response.getUpdateNodeTimestamp() >= resTimestamp) {
                        resUpdateNode = response.getUpdateNode();
                        resTimestamp = response.getUpdateNodeTimestamp();
                    }

                } catch (StatusRuntimeException timeoutException) {
                    System.out.println("Server : " + rank + " ping to server " + i + " failed");
                }
            }
            i++;
        }
        kvStoreService.setUpdateNodeAndTimestampDB(resUpdateNode, resTimestamp);
        System.out.println("Server : " + rank + " Update node propagation done");
        //TODO: handle fault here if sync from update node fails
        if (resUpdateNode != rank) {
            System.out.println("Server : " + rank + " Res update node is : " + resUpdateNode);
            kvStoreGrpc.kvStoreBlockingStub kvSyncStub = stubHashMap.get(resUpdateNode);
            KvStore.SyncMessageRequest.Builder syncReqBuilder = KvStore.SyncMessageRequest.newBuilder();
            syncReqBuilder.setSyncTimestamp(kvStoreService.getMaxTimestamp());
            try {
                //Reconciliating db
                KvStore.SyncMessageResponse syncMessageResponse = kvSyncStub.withDeadlineAfter(5, TimeUnit.SECONDS).syncMessage(syncReqBuilder.build());
                kvStoreService.reconciliateDB(syncMessageResponse.getRowList());
            } catch (StatusRuntimeException e) {
                System.out.println("Server : " + rank + " reconciliation to update node: " + resUpdateNode + " failed");
            }
        }
        System.out.println("Server : " + rank + " Sync done");
        kvStoreService.refreshHashTable();
        server.awaitTermination();
    }
}
