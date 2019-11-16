package grpcserver;

import com.grpc.sample.dynamoStoreGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import kvStore.DynamoStoreService;

import java.io.IOException;
import java.util.HashMap;

public class DynamoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello World");
        int n = Integer.parseInt(args[0]);
        int rank = Integer.parseInt(args[1]);
        int port = Integer.parseInt(args[2]);

        HashMap<Integer, dynamoStoreGrpc.dynamoStoreStub> stubHashMap = new HashMap<>();
        DynamoStoreService dynamoStoreService = new DynamoStoreService(rank, n);
        Server server = ServerBuilder.forPort(port).addService(dynamoStoreService).build();
        server.start();

        int basePort = port - rank;
        for(int i = 0; i < n; i++) {
            if(i != rank) {
                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", basePort + i).usePlaintext().build();
                //TODO: why waitforReady ?
                dynamoStoreGrpc.dynamoStoreStub dynamoStoreStub = dynamoStoreGrpc.newStub(managedChannel).withWaitForReady();
                stubHashMap.put(i, dynamoStoreStub);
            }
        }
        dynamoStoreService.setStubHashMap(stubHashMap);
        server.awaitTermination();
    }
}
