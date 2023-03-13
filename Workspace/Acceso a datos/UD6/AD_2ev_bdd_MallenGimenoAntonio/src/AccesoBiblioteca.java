import java.util.ArrayList;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class AccesoBiblioteca {

	public static Collection conectar(String nombreColeccion) throws ClassNotFoundException, InstantiationException, 
	IllegalAccessException, XMLDBException {
		Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/" + nombreColeccion;
		Collection coleccion = DatabaseManager.getCollection(url, "admin", "admin");
		return coleccion;
	}


	public static void desconectar(Collection coleccion) 
			throws XMLDBException {
		if (coleccion != null) {
			coleccion.close();
		}
	}
	
	
	public static ArrayList<Libro> consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		ArrayList<Libro> libros = new ArrayList<Libro>();
		try {
			coleccion=  conectar("biblioteca");
			Libro libro = null;
			String sentenciaBuscarProductoPorCodigo = 
					"for $libros in /libros/libro\r\n"
					+ "order by $libros/agno descending\r\n"
					+ "return $libros";
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String texto = (String) recurso.getContent();
				libro = new Libro(texto);
				libros.add(libro);
			}
			return libros;
		}finally {
			desconectar(coleccion);
		}
	}

	public static void insertar(Socio socio) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		try {
			coleccion=  conectar("biblioteca");
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			

			String sentenciaInsertarProducto = 
					"update insert " +
							"<socio>\r\n"
							+ "		<dni>"+socio.getDni()+"</dni>\r\n"
							+ "		<nombre>"+socio.getNombre()+"</nombre>\r\n"
							+ "		<localidad>"+socio.getLocalidad()+"</localidad>\r\n"
							+ "		<telefono>"+socio.getTelefono()+"</telefono>\r\n"
							+ "		<correo>"+socio.getCorreo()+"</correo>\r\n"
							+ "</socio>"
							+ "into /socios";

			servicio.query(sentenciaInsertarProducto);
		}finally {
			desconectar(coleccion);
		}

	}

	public static Socio consultarCodigo(String dni) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		try {
			coleccion=  conectar("biblioteca");
			Socio socio = null;
			String sentenciaBuscarProductoPorCodigo = 
					"for $socios in /socios/socio\r\n"
					+ "where $socios/dni='"+dni+"'\r\n"
					+ "return $socios";
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String texto = (String) recurso.getContent();
				socio =new Socio(texto);

			}
			return socio;
		}finally {
			desconectar(coleccion);
		}
	}
	

	public static ArrayList<Prestamo> consultarPrestamo(String dni) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			coleccion=  conectar("biblioteca");
			Prestamo prestamo = null;
			String sentenciaBuscarProductoPorCodigo = 
					"for $prestamos in /prestamos/prestamo\r\n"
					+ "where $prestamos/dni_socio=\""+dni+"\"\r\n"
					+ "return $prestamos";
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String texto = (String) recurso.getContent();
				prestamo =new Prestamo(texto);
				prestamos.add(prestamo);
			}
			return prestamos;
		}finally {
			desconectar(coleccion);
		}
	}


	public static void eliminar(String dni) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		try {
			coleccion=  conectar("biblioteca");

			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String sentenciaEliminarProducto = 
					"update delete " +
							"/socios/socio[dni = '" + dni + "']";
			servicio.query(sentenciaEliminarProducto);
		}finally {
			desconectar(coleccion);
		}

	}

}
