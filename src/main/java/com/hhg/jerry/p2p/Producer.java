package com.hhg.jerry.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Created by lina on 2018/10/17.
 */
public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "test-queue";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(destination);
        for(int i=0;i<50;i++){
            TextMessage textMessage = session.createTextMessage("msg" + i);
            producer.send(textMessage);
            logger.info("product msg : {}", textMessage.getText());
        }
        session.commit();
        connection.close();
    }
}
