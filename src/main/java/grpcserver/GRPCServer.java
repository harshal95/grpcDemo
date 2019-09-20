package grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kvStore.KVStoreService;

import java.io.IOException;

public class GRPCServer {
    public static void main(String []args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090).addService(new KVStoreService()).build();
        server.start();

        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }
}
