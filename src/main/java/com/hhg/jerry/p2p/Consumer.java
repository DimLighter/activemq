package com.hhg.jerry.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Created by lina on 2018/10/17.
 */
public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "test-queue";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener((message)-> {
            try{
                logger.info("consumer receive msg : " + ((TextMessage)message).getText());
            }catch (Exception e){
            }
        });
    }
}
