/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.rpc;

import com.rfidControl.DTO.serverRCPDTO;
import com.rfidControl.main.rfidMainController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        String urlString = "http://" + server.getIp() + ":" + server.getPort();
        try {
            config.setServerURL(new URL(urlString));
            client.setConfig(config);
        } catch (MalformedURLException ex) {
            log.error("Ocurrio un error al iniciar el cliente RCP-XML " + ex.getLocalizedMessage());
        }


    }

    public void sendDataToServer(String data) throws MalformedURLException, XmlRpcException {



        try {
            //  obj = client.execute("random_map", new Object[]{new Integer(-1)});
            //  throw new RuntimeException("La llamada remote deberia haber generado excepcion");
            Boolean resultado = (Boolean) client.execute("Interpreter.readData", new Object[]{data});

            if (resultado) {
                File file = new File("data.txt");
                if (file.exists()) {
                    FileReader fileR = new FileReader(file);
                    BufferedReader br = new BufferedReader(fileR);
                    String line;
                    while ((line = br.readLine()) != null) {
                        client.execute("Interpreter.readData", new Object[]{line});
                    }
                    file.delete();

                }

            } else {
                serializarArchivo(data);
            }

        } catch (XmlRpcException e) {
            System.out.println("Error al enviar los datos al servidor: " + e.getLocalizedMessage());
            log.error("Error al enviar los datos al servidor: " + e.getLocalizedMessage());
            serializarArchivo(data);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo: " + ex.getLocalizedMessage());
            log.error("No se encontro el archivo: " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            log.error("No se encontro el archivo: " + ex.getLocalizedMessage());

        }
    }

    private void serializarArchivo(String data) {
        try {
            FileWriter fileW = new FileWriter(new File("data.txt"), true);
            BufferedWriter bw = new BufferedWriter(fileW);
            bw.write(data + "\n");
            bw.close();
            fileW.close();
        } catch (IOException ex) {
            System.out.println("Error al serializar a archivo: " + ex.getLocalizedMessage());
            log.error("Error al serializar a archivo: " + ex.getLocalizedMessage());

        }

    }
}
