/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author pablo
 */
@Stateless
public class marcaJMS implements marcaJMSLocal {

    @Resource(name = "jms/logger")
    private Queue marcaJMS;
    @Resource(name = "jms/loggerFactory")
    private ConnectionFactory marcaJMSFactory;


    @Override
    public void persistir(Object messageData) {
        try {
            sendJMSMessageToMarcaMDB(messageData);
        } catch (JMSException ex) {
            System.out.println("No se pudo enviar el mensaje " + ex.getMessage());
        }
    }

    private Message createJMSMessageForjmsMarca(Session session, Object messageData) throws JMSException {
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        return om;
    }

    private void sendJMSMessageToMarcaMDB(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = marcaJMSFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(marcaJMS);
            messageProducer.send(createJMSMessageForjmsMarca(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    System.out.println("ERROR AL ENVIAR MENSAJE: " + e.getMessage());
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
