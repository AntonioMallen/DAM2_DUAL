package Actividad1x01;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
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

	public static void insertar(Producto producto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		coleccion = conectar("ColeccionPruebas");
		ResourceIterator iterador =consultarCodigo(producto.getCodigo());
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		if (iterador.hasMoreResources()) {
			System.out.println("Se ha encontrado un producto con ese código en la base de datos.");
		}
		else {
			String sentenciaInsertarProducto = 
					"update insert " +
						"<produc>" +
							"<cod_prod>" + producto.getCodigo() + "</cod_prod>" +
							"<denominacion>" + producto.getDenominacion() + "</denominacion>" +
							"<precio>" + String.format("%.2f", producto.getPrecio()) + "</precio>" +
							"<stock_actual>" + producto.getStockActual() + "</stock_actual>" +
							"<stock_minimo>" + producto.getStockMinimo() + "</stock_minimo>" +
							"<cod_zona>" + producto.getCodigoZona() + "</cod_zona>" +
						"</produc> " +
					"into /productos";
			
			ResourceSet resultados = servicio.query(sentenciaInsertarProducto);
		}
	}
	
	public static ResourceIterator consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		coleccion = conectar("ColeccionPruebas");
		String sentenciaBuscarProductoPorCodigo = 
				"for $prod in /productos/produc " +
						" return $prod";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		return iterador;
	}
	
	public static ResourceIterator consultarCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		coleccion = conectar("ColeccionPruebas");
		String sentenciaBuscarProductoPorCodigo = 
				"for $prod in /productos/produc " +
						" where $prod/cod_prod = " + codigo +
						" return $prod";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		return iterador;
	}

}
