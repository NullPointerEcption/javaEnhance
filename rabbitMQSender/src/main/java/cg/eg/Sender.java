package cg.eg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;

/**
 * direct，只要void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)的时候，
 * 第二个参数传递一个routingKey。
 * 在消费者queueBind(String queue, String exchange, String routingKey)的时候
 * ，多个queue可以绑定到一个exchange上，消息会发给queueBind时候routingKey一样的。
 */
public class Sender {
    private static final String DIRECT_KEY_NAME = "DIRECT";
    private static String exchange2 = "exchange2";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.exchangeDeclare(exchange2, "direct");
            String message = "Hello key1" + new Date(System.currentTimeMillis());
            // 发送消息到队列中
            channel.basicPublish(exchange2, DIRECT_KEY_NAME, null, message.getBytes("UTF-8"));
            System.out.println("1消息发送完成+" + message + "");

            String message1 = "Hello key2" + new Date(System.currentTimeMillis());
            // 发送消息到队列中
            channel.basicPublish(exchange2, "key2", null, message1.getBytes("UTF-8"));//这条消息没有对应的接受者

            System.out.println("2消息发送完成+" + message + "");
        }
    }
}
