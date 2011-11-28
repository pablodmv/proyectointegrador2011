/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.entities.Log;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.jms.TextMessage;

/**
 *
 * @author pablo
 */
@Stateless
public class loggerJMS implements loggerJMSLocal {

    @Resource(name = "jms/logger")
    private Queue bitacoraLogger;
    @Resource(name = "jms/loggerFactory")
    private ConnectionFactory bitacoraLoggerFactory;

    public void log(Object messageData) {
        try {
            sendJMSMessageToBitacoraLogger(messageData);
        } catch (JMSException ex) {
            Logger.getLogger(loggerJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    private Message createJMSMessageForjmsBitacoraLogger(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        //TextMessage tm = session.createTextMessage();
        //tm.setText(messageData.toString());
        return om;
    }

    private void sendJMSMessageToBitacoraLogger(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = bitacoraLoggerFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(bitacoraLogger);
            messageProducer.send(createJMSMessageForjmsBitacoraLogger(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }




    @Override
       public void loggerMessage(String method, String user, String action){
                 //Logueo

            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion(method);
            bit.setUsuario(user);
            bit.setDetalle(action);
            log(bit);
    }
}
