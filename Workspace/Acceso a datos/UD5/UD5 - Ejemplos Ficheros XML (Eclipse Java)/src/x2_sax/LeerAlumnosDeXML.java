package x2_sax;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class LeerAlumnosDeXML {

	public static void main(String[] args) {
		try {
			XMLReader  lectorXML = XMLReaderFactory.createXMLReader();
			MyContentManager mcm = new MyContentManager();  
			lectorXML.setContentHandler(mcm);
			InputSource origen = new InputSource("data/alumnos.xml");	    
			lectorXML.parse(origen);    
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("Error de Fichero No Encontrado: " + fnfe.getMessage());
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("Error de Entrada/Salida: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		catch (SAXException saxe) {
			System.out.println("Error de API Simple para XML: " + saxe.getMessage());
			saxe.printStackTrace();
		}
	}

}
