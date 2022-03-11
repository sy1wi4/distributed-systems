package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

// TODO: reader thread -> call connect function in separate thread?
public class Client {

    public static void main(String[] args) throws IOException {
        out.println("JAVA TCP CLIENT");
        String hostName = "localhost";
        int portNumber = 12345;

        out.println("Starting client");
        try (Socket socket = new Socket(hostName, portNumber)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner stdin = new Scanner(System.in);
            System.out.print("Enter your nickname: ");
            String id = stdin.nextLine();
            out.println(id);
//            new Thread(this::connect).start();

            while (true) {
                String message = stdin.nextLine();
                System.out.printf("Client [%s] sending new message: \"%s\"\n", id, message);
                // send message (+ id)
                out.println(message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
