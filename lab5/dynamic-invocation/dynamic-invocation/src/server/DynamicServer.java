package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class DynamicServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 50050;
        Server server = ServerBuilder
                .forPort(port)
                .addService(ProtoReflectionService.newInstance())
                .addService(new CalculatorImpl())
                .addService(new GeneratorImpl())
                .build()
                .start();


        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                server.shutdown();
                System.err.println("*** server shut down");
            }
        });

        server.awaitTermination();
    }
}
