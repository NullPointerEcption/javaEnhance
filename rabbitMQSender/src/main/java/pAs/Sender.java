package pAs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;

/**
 * 发布订阅者模式下的发布者
 */
public class Sender {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            //交换定义
            channel.exchangeDeclare("exchange1", "fanout");
            String message = "Hello" + new Date(System.currentTimeMillis());
            // 发送消息到队列中
            channel.basicPublish("exchange1", "", null, message.getBytes("UTF-8"));
            System.out.println("消息发送完成+" + message + "");
        }
    }

}
