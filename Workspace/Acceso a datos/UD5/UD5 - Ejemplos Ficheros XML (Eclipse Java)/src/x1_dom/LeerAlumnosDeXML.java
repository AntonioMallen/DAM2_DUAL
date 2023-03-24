package x1_dom;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

public class LeerAlumnosDeXML {
	
	public static void main(String[] args) {
		try {	 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documento = builder.parse(new File("data/alumnos.xml"));
			documento.getDocumentElement().normalize();
			
			System.out.printf("Nodo Raíz: %s %n", documento.getDocumentElement().getNodeName());
			NodeList listaNodosAlumno = documento.getElementsByTagName("alumno");      
			System.out.printf("Número de Nodos alumno: %d %n", listaNodosAlumno.getLength());
			
			for (int i = 0; i < listaNodosAlumno.getLength(); i++) {
				Node nodoAlumno = listaNodosAlumno.item(i);
				System.out.println(nodoAlumno);
				if (nodoAlumno.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodoAlumno;
					String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
					String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
					String nota = element.getElementsByTagName("nota").item(0).getTextContent();
					Alumno alumno = new Alumno(Integer.parseInt(codigo), nombre, Double.parseDouble(nota));
					System.out.println(alumno.toString());
				}
			}
		} 
		catch (ParserConfigurationException pce) {
			System.out.println("Error de Configuración del Analizador Sintáctico: " + pce.getMessage());
			pce.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("Error de Entrada/Salida: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		catch (SAXException saxe) {
			System.out.println("Error de API Simple para XML: " + saxe.getMessage());
			saxe.printStackTrace();
		}
		catch (DOMException dome) {
			System.out.println("Error de Modelo de Objetos de Documento: " + dome.getMessage());
			dome.printStackTrace();
		}
	}
	
}
