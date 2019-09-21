package grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kvStore.KVStoreService;

import java.io.IOException;
import java.util.Arrays;

public class GRPCServer {
    public static void main(String []args) throws IOException, InterruptedException {
//        String inputParams[] = args[0].split(" ");
        int n = Integer.parseInt(args[0]);
        int rank = Integer.parseInt(args[1]);
        int updateNode = Integer.parseInt(args[2]);
        int port = Integer.parseInt(args[3]);
        System.out.println("length of array: " +  args.length);
        System.out.println(Arrays.toString(args));

        Server server = ServerBuilder.forPort(port).addService(new KVStoreService()).build();
        server.start();

        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }
}
