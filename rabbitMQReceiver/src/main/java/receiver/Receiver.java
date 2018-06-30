package receiver;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 普通的消费者
 */
public class Receiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
        ) {
            channel.queueDeclare("wyfQueue", true, false, false, null);
            System.out.println("等待接受消息");
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body, "UTF-8");
                    System.out.println("收到了消息：" + msg);
                    //...一系列操作

                    //一直没有异常 确认收到消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            //第二个参数表示自动回复队列应答
            //channel.basicConsume("wyfQueue",true,consumer);//当wyfQueue队列中有消息时 请调用wyfQueue的handleDelivery通知给我
            //不自动确认 需要手动确认
            channel.basicConsume("wyfQueue", false, consumer);//当wyfQueue队列中有消息时 请调用wyfQueue的handleDelivery通知给我
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
