/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

import com.rfidControl.Readers.alienReader;
import com.rfidControl.abstractClasses.Reader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private final static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        PropertyConfigurator.configure("src/com/rfidControl/log4j/log4j.properties");
        List<Reader> readers = new ArrayList<Reader>();
        readers = readerContstruct();
        rfidMainController controller = rfidMainController.getInstance();
        for (Reader reader : readers) {
            if (controller.connectReader(reader)) {
                new rfidReadFromReader(reader).start();
//            while (true) {
//                try {
//                    System.out.println(reader.read());
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    log.error("Error metodo main: " + ex.getMessage());
//                } catch (Exception ex) {
//                    log.error("Error metodo main: " + ex.getMessage());
//                }
//            }
            } else {
                log.error("No se puede conectar al dispositivo :" + reader.getName() + "- " + reader.getIp_addr());
            }
        }
//        Reader reader1 = readers.get(0);
//        Reader reader2 = readers.get(1);
//        controller.connectReader(reader1);
//        controller.connectReader(reader2);
//       
//        while (true) {
//            try {
//                System.out.println(reader1.read());
//                System.out.println(reader2.read());
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        





    }

    public static List<Reader> readerContstruct() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        List<Reader> readerRetorno = new ArrayList<Reader>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("src/com/rfidControl/readersDefinitions/readerDefinitions.xml"));
            documento.getDocumentElement().normalize();
            NodeList readers = documento.getElementsByTagName("reader");
            for (int i = 0; i < readers.getLength(); i++) {
                Node reader = readers.item(i);
                if (reader.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) reader;
                    Reader readerAux = new alienReader(getTagValue("net-address", elemento), getTagValue("name", elemento),Short.parseShort(getTagValue("port", elemento)));
                    readerRetorno.add(readerAux);
                }
            }
            return readerRetorno;

//            Node nodoRaiz = documento.getFirstChild();
//            NodeList listaNodosHijos = nodoRaiz.getChildNodes();
//            for (int i = 0; i < listaNodosHijos.getLength(); i++) {
//                Node unNodoHijo = listaNodosHijos.item(i);
//                NamedNodeMap atributos = unNodoHijo.getAttributes();
//                Node unAtributo = atributos.getNamedItem("nombre_atributo");
//                String valorAtributo = unAtributo.getNodeValue();
//                System.out.println(valorAtributo);
//            }
        } catch (SAXException ex) {
            log.error("SAXException : " + ex.getMessage());
        } catch (IOException ex) {
            log.error("Error al abrir archivo : " + ex.getMessage());
        } catch (ParserConfigurationException ex) {
            log.error("Error al parsear documento : " + ex.getMessage());
        }

        return null;
    }

    public static String getTagValue(String tag, Element elemento) {

        NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
        Node valor = (Node) lista.item(0);
        return valor.getNodeValue();

    }
}
