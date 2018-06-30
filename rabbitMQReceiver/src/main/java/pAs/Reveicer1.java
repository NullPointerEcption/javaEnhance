package pAs;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 发布订阅者模式下的接受者
 */
public class Reveicer1 {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.exchangeDeclare("exchange1", "fanout");

            //创建一个没有名字的队列
            String queueName = channel.queueDeclare("", true, false, false, null).getQueue();
            channel.queueBind(queueName, "exchange1", "");//把队列绑定到exechange上

            System.out.println("等待接收消息");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("接受者11111收到消息 " + message + "，是否再次发送" + envelope.isRedeliver());
                    //...可能会有异常的代码
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            //第二个参数表示自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume(queueName, false, consumer);
            while (true) {
                Thread.sleep(1000);
            }
        }
    }
}
