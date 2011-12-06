/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

import com.rfidControl.DTO.serverRCPDTO;
import com.rfidControl.Readers.alienReader;
import com.rfidControl.abstractClasses.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
public class rfidMainController {

    private static rfidMainController instance = null;
    private List<Reader> readersCollection = null;
    private final static Logger log = Logger.getLogger(rfidMainController.class);

    private rfidMainController() {
        readersCollection = new ArrayList<Reader>();

        PropertyConfigurator.configure("src/com/rfidControl/log4j/log4j.properties");

    }

    public static rfidMainController getInstance() {
        if (instance == null) {
            instance = new rfidMainController();
        }
        return instance;
    }

    public List<Reader> getReadersCollection() {
        return readersCollection;
    }

    public void setReadersCollection(List<Reader> readersCollection) {
        this.readersCollection = readersCollection;
    }

    public boolean connectReader(Reader reader) {
        if (!reader.connect()) {
            return false;
            //TODO: loggear
        } else {
            return true;
        }
    }

    public void readFromReaders() {
        readersCollection = readerContstruct();

        for (Reader readers : readersCollection) {
            if (connectReader(readers)) {
                new rfidReadFromReader(readers).start();
            } else {
                log.error("No se puede conectar al dispositivo :" + readers.getName() + "- " + readers.getIp_addr());
                System.out.println("No se puede conectar al dispositivo :" + readers.getName() + "- " + readers.getIp_addr());
            }
        }
//        String data = reader.read();
//        try {
//            rcpConnection.sendDataToServer(data);
//
//        } catch (MalformedURLException ex) {
//            log.error("Ocurrio un error al enviar los datos " + ex.getLocalizedMessage());
//        } catch (XmlRpcException ex) {
//            log.error("Ocurrio un error al enviar los datos " + ex.getLocalizedMessage());
//        }
//        return data;

    }

    public void persistReadersActive(Reader reader) {
    }

    public void deleteReaderInactive(Reader reader) {

        for (Reader readerActive : readersCollection) {
            if (readerActive.getName().equals(reader.getName())) {
                readersCollection.remove(reader);
            }
        }
    }

    private List<Reader> readerContstruct() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        List<Reader> readerRetorno = new ArrayList<Reader>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("configuration/readerDefinitions.xml"));
            documento.getDocumentElement().normalize();
            NodeList readers = documento.getElementsByTagName("reader");
            for (int i = 0; i < readers.getLength(); i++) {
                Node reader = readers.item(i);
                if (reader.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) reader;
                    Reader readerAux = new alienReader(getTagValue("net-address", elemento), getTagValue("name", elemento), Short.parseShort(getTagValue("port", elemento)));
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

    private String getTagValue(String tag, Element elemento) {

        NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
        Node valor = (Node) lista.item(0);
        return valor.getNodeValue();

    }

    public String convertStringToFormat(String data, Reader reader) {
        String tags = "";
        String tagsAux2 = "";
        if (data.length() > 50) {
            tagsAux2 = data.split("Tag:")[1];
            if (tagsAux2 != null) {
                tags = tagsAux2.split(",")[0];
                tags += "," + tagsAux2.split(",")[2].split("Last:")[1].replace(" ", ",");
                tags += "," + reader.getIp_addr()+ ":" + reader.getPort()+ ":"+tagsAux2.split(",")[4].split(":")[1];
                tags += "," + reader.getDescription();
            }

        }
        return tags;
    }

    public serverRCPDTO readProperties() {
        Properties props = new Properties();
        serverRCPDTO server = new serverRCPDTO();
        try {
            props.load(new FileInputStream("configuration/Configuration.properties"));
            server.setIp(props.getProperty("REMOTE_SERVER_IP"));
            server.setPort(props.getProperty("REMOTE_SERVER_PORT"));
            return server;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
