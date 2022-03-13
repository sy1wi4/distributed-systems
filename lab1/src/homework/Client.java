package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Client {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
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

            executor.execute(listen(in));

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
