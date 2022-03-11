package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

// after new client connection request is processed here,
// not in the same thread that accepts the client connection
public class ServerClientThread implements Runnable {
    private List<ServerClientThread> clients;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String id;

    public ServerClientThread(Socket clientSocket, List<ServerClientThread> clients) throws IOException {
        this.clients = clients;
        socket = clientSocket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        // send to all
        try {
            id = in.readLine().trim();
            System.out.println("Client connected, id: " + id);

            while (true) {
                String message = in.readLine().trim();
                System.out.printf("New message from client [%s]: \"%s\"\n", id, message);
                System.out.println("Sending to other clients...");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: finally

    }

    private void sendMessage(String message) {
        out.println(message);
    }
}
