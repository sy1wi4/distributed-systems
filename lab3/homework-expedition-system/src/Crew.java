import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Crew {
    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter crew name: ");
        String crewName = br.readLine();

        System.out.printf("[%s] crew here%n", crewName);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME = "exchange_exp";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // TODO: confirmation exchange

        while (true) {
            System.out.println("Type equipment you need: ");
            String eq = br.readLine();
            String msg = eq + ":" + crewName;  // to handle confirmation

            if ("exit".equals(eq)) {
                break;
            }

            channel.basicPublish(EXCHANGE_NAME, eq, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent: " + msg);
        }
    }
}