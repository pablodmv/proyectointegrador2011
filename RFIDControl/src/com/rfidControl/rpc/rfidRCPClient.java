/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.rpc;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


public class rfidRCPClient {

    public static void dump(Object obj) {
        System.out.println("======================================================================");
        System.out.println("Clase: " + obj.getClass());
        System.out.println("toString(): " + obj.toString());

        if (obj.getClass().isArray()) {
            Object[] objs = (Object[]) obj;
            System.out.println("---------- ARRAY ----------");
            System.out.println("Tamanio: " + objs.length);
            for (int i = 0; i < objs.length; i++) {
                System.out.println("Object[" + i + "]: " + objs[i]);
            }
        }
    }

    public static void main(String args[]) throws MalformedURLException, XmlRpcException {
        final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://127.0.0.1:8000"));
        final XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        Object obj = client.execute("add", new Object[]{new Integer(33), new Integer(9)});
        dump(obj);

        obj = client.execute("random_list", new Object[]{new Integer(14)});
        dump(obj);

        obj = client.execute("random_map", new Object[]{new Integer(9)});
        dump(obj);

        try {
            obj = client.execute("random_map", new Object[]{new Integer(-1)});
            throw new RuntimeException("La llamada remote deberia haber generado excepcion");
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }
}
