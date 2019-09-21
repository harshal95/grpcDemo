package grpcserver;

import com.grpc.sample.kvStoreGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import kvStore.KVStoreService;

import java.io.IOException;
import java.util.Arrays;

public class GRPCServer {
    public static void main(String []args) throws IOException, InterruptedException {
        int n = Integer.parseInt(args[0]);
        int rank = Integer.parseInt(args[1]);
        int updateNode = Integer.parseInt(args[2]);
        int port = Integer.parseInt(args[3]);
        System.out.println("length of array: " +  args.length);
        System.out.println(Arrays.toString(args));

        Server server = ServerBuilder.forPort(port).addService(new KVStoreService(updateNode, rank, n)).build();
        server.start();

        int basePort = port - rank;
        for(int i = 0; i < n; i++) {
            if(i != rank) {
                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", basePort + i).usePlaintext().build();
                kvStoreGrpc.kvStoreBlockingStub kvStub = kvStoreGrpc.newBlockingStub(managedChannel).withWaitForReady();
//                kvStor loginRequest = User.LoginRequest.newBuilder().setUsername("harshal").setPassword("harshal").build();
//                User.APIResponse response = userStub.login(loginRequest);
            }
        }
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }
}
