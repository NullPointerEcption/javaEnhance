package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;

/**
 * 普通的发送者
 */
public class Sender {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
        ) {
            channel.queueDeclare("wyfQueue", true, false, false, null);
            channel.basicPublish("", "wyfQueue", null, new String("Hello " + new Date()).getBytes());
            System.out.println("消息发送完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
