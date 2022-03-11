package homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static final List<ServerClientThread> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        System.out.println("--- SERVER ---");
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            // create socket

            while (true) {

                // accept client
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client on server");

                ServerClientThread client = new ServerClientThread(clientSocket, clients);
                clients.add(client);
                new Thread(client).start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
