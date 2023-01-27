package x2_sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

class MyContentManager2 extends DefaultHandler {
	
	// lista de alumnos
	private List<Alumno> listaAlumnos;
	
	private String texto;
	private Alumno alumno;
	
	// Constructor
	public MyContentManager2() {
		super();
		listaAlumnos = new ArrayList<Alumno>();
	}
	
	// Asociado al evento de comienzo del documento XML.
	public void startDocument()
	throws SAXException {
	}
	
	// Asociado al evento de fin del documento XML.
	public void endDocument()
	throws SAXException {
	}
	
	// Asociado al evento de comienzo de un elemento.
	public void startElement(String uri, String nombreLocal, String nombreCualificado, Attributes atributos) 
	throws SAXException {
		if (nombreLocal.equals("alumno")) {
			alumno = new Alumno();
			for (int i = 0 ; i < atributos.getLength() ; i++) {
				alumno.insertar(atributos.getQName(i), atributos.getValue(i));
			}
		}
	}
	
	// Asociado al evento de fin de un elemento.
	public void endElement(String uri, String nombreLocal, String nombreCualificado)
	throws SAXException {
		if (nombreLocal.equals("alumno")) {
			listaAlumnos.add(alumno);
		}
		else if (nombreLocal.equals("codigo")) {
			alumno.setCodigo(Integer.parseInt(texto));
		}
		else if (nombreLocal.equals("nombre")) {
			alumno.setNombre(texto);
		}
		else if (nombreLocal.equals("nota")) {
			alumno.setNota(Double.parseDouble(texto));
		}
	}
	
	// Asociado al evento de una cadena de caracteres.
	public void characters(char[] vectorCaracteres, int inicio, int longitud) 
	throws SAXException {
		String cadena = new String(vectorCaracteres, inicio, longitud);
		cadena = cadena.replaceAll("[\t\n]", "");
		cadena = cadena.trim();
		texto = cadena;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}	
	
}
