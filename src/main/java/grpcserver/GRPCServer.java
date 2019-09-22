package grpcserver;

import com.grpc.sample.kvStoreGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import kvStore.KVStoreService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class GRPCServer {

    public static void main(String []args) throws IOException, InterruptedException {
        int n = Integer.parseInt(args[0]);
        int rank = Integer.parseInt(args[1]);
        int updateNode = Integer.parseInt(args[2]);
        int port = Integer.parseInt(args[3]);
        HashMap<Integer, kvStoreGrpc.kvStoreBlockingStub> stubHashMap = new HashMap<>();
        System.out.println("length of array: " +  args.length);
        System.out.println(Arrays.toString(args));
        KVStoreService kvStoreService = new KVStoreService(updateNode, rank, n);
        Server server = ServerBuilder.forPort(port).addService(kvStoreService).build();
        server.start();

        int basePort = port - rank;
        for(int i = 0; i < n; i++) {
            if(i != rank) {
                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", basePort + i).usePlaintext().build();
                kvStoreGrpc.kvStoreBlockingStub kvStub = kvStoreGrpc.newBlockingStub(managedChannel).withWaitForReady();
                stubHashMap.put(i, kvStub);
            }
        }
        System.out.println("Server started at " + server.getPort());
        kvStoreService.setStubHashMap(stubHashMap);
        server.awaitTermination();
    }
}
