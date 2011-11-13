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
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Reader> readers = new ArrayList<Reader>();
        readers=readerContstruct();
        rfidMainController controller = rfidMainController.getInstance();
        Reader reader = readers.get(0);
        if (controller.connectReader(reader)) {
            while (true) {
                try {
                    System.out.println(reader.read());
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("Ocurrio un error");
        }
    }

    public static List<Reader> readerContstruct() {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        List<Reader> readerRetorno= new ArrayList<Reader>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
                documento = builder.parse(new File("src/com/rfidControl/readersDefinitions/readerDefinitions.xml"));
            documento.getDocumentElement().normalize();
            NodeList readers = documento.getElementsByTagName("reader");
            for (int i = 0; i < readers.getLength(); i++) {
                Node reader = readers.item(i);
                if (reader.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) reader;
                    Reader readerAux = new alienReader(getTagValue("net-address", elemento), getTagValue("name", elemento));
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
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static String getTagValue(String tag, Element elemento) {

        NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
        Node valor = (Node) lista.item(0);
        return valor.getNodeValue();

    }
}
