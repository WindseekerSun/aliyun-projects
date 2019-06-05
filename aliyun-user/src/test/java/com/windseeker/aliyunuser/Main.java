package com.windseeker.aliyunuser;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;

class send extends Thread {
    private final static String QUEUE_NAME = "hello";

    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.6.28.46");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Random randomIntNumber = new Random();

        try {
            while (true) {
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!";
                Long ThreadID = send.currentThread().getId();
                message = message + " Thread ID: " + ThreadID.toString() + " Random Num: " + randomIntNumber.nextInt();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Sent '" + message + "'");
                System.out.println("");
                channel.close();
                connection.close();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class receiver extends Thread {
    private final static String QUEUE_NAME = "hello";

    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.22.151");
        factory.setPort(5672);
        factory.setUsername("windseeker");
        factory.setPassword("060615qf");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) ;
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
                String message = ((DefaultConsumer) consumer).getConsumerTag();
                System.out.println("Received '" + message + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        send send = new send();
        send.start();

        receiver receiver = new receiver();
        receiver.start();
    }
}
