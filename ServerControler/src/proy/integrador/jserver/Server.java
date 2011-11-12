package proy.integrador.jserver;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class Server {

	 private static final int port = 8000;
	/**
	 * @param args
	 *   
	 */
	public static void main(String[] args) {
		
		final WebServer webServer = new WebServer(port);
		final XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
		final PropertyHandlerMapping phm = new PropertyHandlerMapping();
		System.out.println("Servidor inicializado");
		try {
			phm.addHandler("Interpreter", proy.integrador.jserver.Interpreter.class);
			xmlRpcServer.setHandlerMapping(phm);
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(true); 
			serverConfig.setContentLengthOptional(false);
			webServer.start();
			System.out.println("Servidor escuchando");
		}catch (IOException e){
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}

	}

}
