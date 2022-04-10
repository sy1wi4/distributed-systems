import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Supplier {
    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter supplier name: ");
        String supplierName = br.readLine();

        System.out.printf("[%s] supplier here%n", supplierName);

        System.out.println("Enter supported equipment types [eq1:eq2:...:eqn]:");
        String[] equipments = br.readLine().split(":");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);

        String EXCHANGE_EXP = "exchange_exp";
        channel.exchangeDeclare(EXCHANGE_EXP, BuiltinExchangeType.DIRECT);

        String EXCHANGE_CONF = "exchange_conf";
        channel.exchangeDeclare(EXCHANGE_CONF, BuiltinExchangeType.DIRECT);

        for (String eq : equipments) {
            String queueName = channel.queueDeclare(eq, false, false, false, null).getQueue();
            channel.queueBind(queueName, EXCHANGE_EXP, eq);
            System.out.println("created queue: " + eq);
        }

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                String[] split = message.split(":");
                String eq = split[0];
                String senderCrew = split[1];
                System.out.printf("Received [%s] order from [%s]%n", eq, senderCrew);
                String reply = "[" + eq + "] order delivered";
                channel.basicPublish(EXCHANGE_CONF, senderCrew, null, reply.getBytes(StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        System.out.println("Waiting for messages...");
        for (String eq : equipments) {
            channel.basicConsume(eq, false, consumer);
        }
    }
}