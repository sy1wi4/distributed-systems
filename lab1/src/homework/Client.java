package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) {
        System.out.println("--------- CLIENT ---------");
        String hostName = "localhost";
        int portNumber = 12345;

        System.out.println("Starting client");
        try (Socket socket = new Socket(hostName, portNumber)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner stdin = new Scanner(System.in);
            System.out.print("Enter your nickname: ");
            String id = stdin.nextLine();
            out.println(id);

            new Thread(listen(in)).start();

            while (true) {
                String message = stdin.nextLine();
                out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // listen for incoming messages (in another thread)
    private static Runnable listen(BufferedReader in) {
        return () -> {
            try {
                while (true) {
                    String message = in.readLine().trim();
                    System.out.printf("Received message: \"%s\"%n", message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
