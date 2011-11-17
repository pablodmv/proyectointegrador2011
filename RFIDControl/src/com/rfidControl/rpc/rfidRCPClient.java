/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.rpc;

import com.rfidControl.DTO.serverRCPDTO;
import com.rfidControl.main.rfidMainController;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class rfidRCPClient {

    private static rfidRCPClient instance;
    private final XmlRpcClient client = new XmlRpcClient();
    private final static Logger log = Logger.getLogger(rfidRCPClient.class);

    private rfidRCPClient() {
        init(); //inicializa conexion con el servidor
        PropertyConfigurator.configure("src/com/rfidControl/log4j/log4j.properties");
    }

    public static rfidRCPClient getInstance() {
        if (instance == null) {
            instance = new rfidRCPClient();
        }
        return instance;
    }

    private void init() {
        final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        rfidMainController controller = rfidMainController.getInstance();
       serverRCPDTO server = controller.readProperties();
       String urlString = "http://" + server.getIp() + ":"+server.getPort();
        try {
            config.setServerURL(new URL(urlString));

            client.setConfig(config);
        } catch (MalformedURLException ex) {
            log.error("Ocurrio un error al iniciar el cliente RCP-XML " + ex.getLocalizedMessage());
        }


    }


    public void sendDataToServer(String data) throws MalformedURLException, XmlRpcException {
       
        Object obj = client.execute("Interpreter.readData", new Object[] { data });

//        try {
//            obj = client.execute("random_map", new Object[]{new Integer(-1)});
//            throw new RuntimeException("La llamada remote deberia haber generado excepcion");
//        } catch (XmlRpcException e) {
//            e.printStackTrace();
//        }
    }
}
