package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

// after new client connection request is processed here,
// not in the same thread that accepts the client connection
public class ClientHandler implements Runnable {
    private final List<ClientHandler> clients;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String id;

    public ClientHandler(Socket clientSocket, List<ClientHandler> clients) throws IOException {
        this.clients = clients;
        socket = clientSocket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        // new server client -> read id, read messages, send to all
        try {
            id = in.readLine().trim();
            System.out.println("Client connected, id: " + id);

            while (true) {
                String message = in.readLine().trim();
                System.out.printf("Sending new message from [%s] to other clients...%n", id);

                for (ClientHandler client : clients) {
                    if (!client.getId().equals(id)) {
                        System.out.printf("...sending to [%s]%n", client.getId());
                        client.out.println(String.format("%s:%s", id, message));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }
}
