import com.rabbitmq.client.*;

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

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_EXP = "exchange_exp";
        channel.exchangeDeclare(EXCHANGE_EXP, BuiltinExchangeType.DIRECT);

        String EXCHANGE_CONF = "exchange_conf";
        channel.exchangeDeclare(EXCHANGE_CONF, BuiltinExchangeType.DIRECT);

        String confQueue = channel.queueDeclare(crewName, false, false, false, null).getQueue();
        channel.queueBind(confQueue, EXCHANGE_CONF, crewName);
        System.out.println("created confirmation queue: " + crewName);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.printf("Confirmation: %s%n", message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(crewName, false, consumer);

        while (true) {
            System.out.println("Type equipment you need: ");
            String eq = br.readLine();
            String msg = eq + ":" + crewName;  // to handle confirmation

            if ("exit".equals(eq)) {
                break;
            }

            channel.basicPublish(EXCHANGE_EXP, eq, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent: " + msg);
        }

        channel.close();
        connection.close();
    }
}