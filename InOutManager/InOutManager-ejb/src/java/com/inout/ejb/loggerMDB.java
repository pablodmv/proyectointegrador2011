/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.entities.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pablo
 */
@MessageDriven(mappedName = "jms/logger", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class loggerMDB implements MessageListener {

     @PersistenceContext
    private EntityManager em;

    public loggerMDB() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            ObjectMessage om = (ObjectMessage) message;
            Log bitacora = (Log)om.getObject();


            em.persist(bitacora);
        } catch (JMSException ex) {
            Logger.getLogger(loggerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
