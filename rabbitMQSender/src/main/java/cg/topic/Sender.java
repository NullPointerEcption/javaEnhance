package cg.topic;

import com.rabbitmq.client.*;

import java.util.Date;

public class Sender {
    private static String changeType = "topic";
    private static String exchange3 = "exchange3";
    private static String routingKey1 = "rupeng.com";
    private static String routingKey2 = "rupeng.yes.123";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.exchangeDeclare(exchange3, changeType);
            String message = "Hello key1111111" + new Date(System.currentTimeMillis());
            // 发送消息到队列中
            channel.basicPublish(exchange3, routingKey1, null, message.getBytes("UTF-8"));
            System.out.println("1消息发送完成+" + message + "");

            String message1 = "Hello key2222222" + new Date(System.currentTimeMillis());
            // 发送消息到队列中
            channel.basicPublish(exchange3, routingKey2, null, message1.getBytes("UTF-8"));

            System.out.println("2消息发送完成+" + message + "");
        }
    }
}
