package Actividad1x02;

import java.util.ArrayList;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import entrada.Teclado;

public class AccesoDatos {

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

	public static boolean insertar(Zona zona) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion =  conectar("Coleccion2ej");
		Zona comZona = consultarCodigo(zona.getCodigo());

		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		if (comZona!=null) {// si existe
			desconectar(coleccion);
			return false;
		}else {
			String sentenciaInsertarProducto = 
					"update insert " +
							"<zona>" +
							"<cod_zona>" + zona.getCodigo() + "</cod_zona>" +
							"<nombre>" + zona.getNombre() + "</denominacion>" +
							"<director>" + String.format("%.2f", zona.getDirector()) + "</precio>" +
							"</zona> " +
							"into /productos";

			ResourceSet resultados = servicio.query(sentenciaInsertarProducto);
			desconectar(coleccion);
			return true;
		}
	}

	public static ArrayList<Zona> consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("Coleccion2ej");
		ArrayList<Zona> zonas = new ArrayList<Zona>();
		String sentenciaBuscarProductoPorCodigo = 
				"for $zona in /zonas" +
						" return <item>{$zona/cod_zona/text()"
						+ "},{"+ "$zona/nombre/text() "
						+ "},{"+ "$zona/director/text()";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		while (iterador.hasMoreResources()) {
			Resource recurso = iterador.nextResource();
			String zonaMal = (String) recurso.getContent();
			zonaMal=zonaMal.replace("<item>", "");
			zonaMal=zonaMal.replace("</item>", "");
			String lista[] = zonaMal.split(",");
			Zona zona =new Zona(Integer.parseInt(lista[0]),lista[1],lista[2]);
			zonas.add(zona);
		}
		desconectar(coleccion);
		return zonas;
	}

	public static Zona consultarCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("Coleccion2ej");
		Zona zona = null;
		String sentenciaBuscarProductoPorCodigo = 
				"for $zona in /zonas" +
						" where $zona/cod_zona = " + codigo +
						" return <item>{$zona/cod_zona/text()"
						+ "},{"+ "$zona/nombre/text() "
						+ "},{"+ "$zona/director/text()";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		while (iterador.hasMoreResources()) {
			Resource recurso = iterador.nextResource();
			String zonaMal = (String) recurso.getContent();
			zonaMal=zonaMal.replace("<item>", "");
			zonaMal=zonaMal.replace("</item>", "");
			String lista[] = zonaMal.split(",");
			zona =new Zona(Integer.parseInt(lista[0]),lista[1],lista[2]);

		}
		desconectar(coleccion);
		return zona;
	}

	public static void actualizar(Zona zona) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("Coleccion2ej");
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

		String sentenciaActualizarProducto = 
				"update replace " +
						"/zonas/zona[cod_zona = " + zona.getCodigo() + "] with " +
						"<zona>" +
						"<cod_zona>" + zona.getCodigo() + "</cod_zona>" +
						"<nombre>" + zona.getNombre() + "</denominacion>" +
						"<director>" + String.format("%.2f", zona.getDirector()) + "</precio>" +
						"</zona> ";	
		servicio.query(sentenciaActualizarProducto);
	}

	public static void eliminar(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("Coleccion2ej");
		
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String sentenciaEliminarProducto = 
					"update delete " +
							"/productos/produc[cod_prod = " + codigo + "]";
			servicio.query(sentenciaEliminarProducto);
		
	}

}
