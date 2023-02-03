package x1_dom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class EscribirAlumnosEnXML {

	// Lee todos los alumnos de un fichero binario con acceso aleatorio.
	// Devuelve una lista con los alumnos leídos del fichero binario.
	public static List<Alumno> leerAlumnosDeBinario() 
	throws IOException {
		RandomAccessFile flujoEntrada = null;
		try {
			List<Alumno> listaAlumnos = new ArrayList<Alumno>();
			File fichero = new File("data/alumnos_aleat_datos.dat");
			flujoEntrada = new RandomAccessFile(fichero, "r");
			flujoEntrada.seek(0);			
			while (flujoEntrada.getFilePointer() < flujoEntrada.length()) {
				int codigo = flujoEntrada.readInt();
				char[] vectorCaracteres = new char[Alumno.LONGITUD_NOMBRE];
				for (int i = 0 ; i < vectorCaracteres.length ; i++) {         
					vectorCaracteres[i] = flujoEntrada.readChar(); 
				}
				String nombre = new String(vectorCaracteres); 
				double nota = flujoEntrada.readDouble();
				if (codigo > 0) {
					Alumno alumno = new Alumno(codigo, nombre, nota);
					listaAlumnos.add(alumno);
				}
			}
			return listaAlumnos;
		}
		finally {
			if (flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
	}
	
	// Crea un nodo con una etiqueta y un texto bajo un nodo padre del documento DOM.
	public static void crearNodo(String etiqueta, String texto, Element padre, Document documento)
	throws DOMException {
		Element nodoElemento = documento.createElement(etiqueta);
		Text nodoTexto = documento.createTextNode(texto);
		nodoElemento.appendChild(nodoTexto);	
		padre.appendChild(nodoElemento);
	}
	
	// Lee todos los alumnos del fichero binario 'alumnos_aleat_datos.dat',
	// escribe estos alumnos en el fichero XML 'alumnos.xml' y
	// escribe en consola el número de alumnos escritos en el fichero XML.
	public static void main(String args[]) {
		try {
			List<Alumno> listaAlumnos = leerAlumnosDeBinario();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document documento = implementation.createDocument(null, "alumnos", null);
			documento.setXmlVersion("1.0"); 
			
			for (Alumno alumno : listaAlumnos) {
				int codigo = alumno.getCodigo();
				String nombre = alumno.getNombre();
				double nota = alumno.getNota();
				if (codigo > 0) {
					Element raiz = documento.createElement("alumno");
					documento.getDocumentElement().appendChild(raiz); 
					crearNodo("codigo", Integer.toString(codigo), raiz, documento); 
					crearNodo("nombre", nombre.trim(), raiz, documento); 
					crearNodo("nota", Double.toString(nota), raiz, documento); 
				}
			}
			
			Source origen = new DOMSource(documento);
			Result destino = new StreamResult(new File("data/alumnos.xml"));
			Transformer transformador = TransformerFactory.newInstance().newTransformer();
			transformador.transform(origen, destino);
			
			System.out.println("Se han escrito " + listaAlumnos.size() + 
			                   " alumnos en el fichero XML.");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("Error de Fichero No Encontrado: " + fnfe.getMessage());
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("Error de Entrada/Salida: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		catch (ParserConfigurationException pce) {
			System.out.println("Error de Configuración del Analizador Sintáctico: " + pce.getMessage());
			pce.printStackTrace();
		}
		catch (DOMException dome) {
			System.out.println("Error de Modelo de Objetos de Documento: " + dome.getMessage());
			dome.printStackTrace();
		}
		catch (TransformerConfigurationException tce) {
			System.out.println("Error de Configuración del Transformador: " + tce.getMessage());
			tce.printStackTrace();
		}
		catch (TransformerException te) {
			System.out.println("Error de Transformación: " + te.getMessage());
			te.printStackTrace();
		}
	}
	
}
