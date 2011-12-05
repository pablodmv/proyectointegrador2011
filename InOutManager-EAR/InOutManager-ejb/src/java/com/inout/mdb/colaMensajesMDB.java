/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.mdb;

import com.inout.ejb.marcaLocal;
import com.inout.entities.Log;
import com.inout.entities.Marca;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
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
public class colaMensajesMDB implements MessageListener {

     @PersistenceContext
    private EntityManager em;
     @EJB
     marcaLocal marcaEJB;

    public colaMensajesMDB() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        if (objectMessage!=null) {
            try {
                if (objectMessage.getObject() instanceof Marca) {
                    Marca marca = (Marca) objectMessage.getObject();
                    persistirMarca(marca);
                }else if (objectMessage.getObject() instanceof Log) {
                    Log log = (Log) objectMessage.getObject();
                    persistirLog(log);
                }
            } catch (JMSException ex) {
                Logger.getLogger(colaMensajesMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }



    private void persistirLog(Log log){
        try {
            em.persist(log);
            em.flush();
        } catch (Exception e) {
            System.out.println("No se pudo guardar el log " +e.getMessage());
        }
    }

    private void persistirMarca(Marca marca){
        try {
            em.persist(marca);
            em.flush();
            marcaEJB.formarParejas(marca);
        } catch (Exception e) {
            System.out.println("No se pudo guardar la marca " + e.getMessage());
        }
    }
    
}
