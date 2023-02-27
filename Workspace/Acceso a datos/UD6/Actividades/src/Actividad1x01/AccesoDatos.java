package Actividad1x01;

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

	public static boolean insertar(Producto producto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		coleccion = conectar("ColeccionPruebas");
		Producto comProducto = consultarCodigo(producto.getCodigo());

		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		if (comProducto!=null) {// si existe
			desconectar(coleccion);
			return false;
		}else {
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
			desconectar(coleccion);
			return true;
		}
	}

	public static ArrayList<Producto> consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("ColeccionPruebas");
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String sentenciaBuscarProductoPorCodigo = 
				"for $prod in /productos/produc " +
						" return <item>{$prod/cod_prod/text()"
						+ "},{"+ "$prod/denominacion/text() "
						+ "},{"+ "$prod/precio/text() "
						+ "},{"+ "$prod/stock_actual/text() "
						+ "},{"+ "$prod/stock_minimo/text() "
						+ "},{"+ "$prod/cod_zona/text()}</item>";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		while (iterador.hasMoreResources()) {
			Resource recurso = iterador.nextResource();
			String producto = (String) recurso.getContent();
			producto=producto.replace("<item>", "");
			producto=producto.replace("</item>", "");
			String lista[] = producto.split(",");
			Producto productoObj =new Producto(Integer.parseInt(lista[0]),lista[1],Double.parseDouble(lista[2]),Integer.parseInt(lista[3]),Integer.parseInt(lista[4]),Integer.parseInt(lista[5]));
			productos.add(productoObj);
		}
		desconectar(coleccion);
		return productos;
	}

	public static Producto consultarCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("ColeccionPruebas");
		Producto productoObj = null;
		String sentenciaBuscarProductoPorCodigo = 
				"for $prod in /productos/produc " +
						" where $prod/cod_prod = " + codigo +
						" return <item>{$prod/cod_prod/text()"
						+ "},{"+ "$prod/denominacion/text() "
						+ "},{"+ "$prod/precio/text() "
						+ "},{"+ "$prod/stock_actual/text() "
						+ "},{"+ "$prod/stock_minimo/text() "
						+ "},{"+ "$prod/cod_zona/text()}</item>";
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);

		ResourceIterator iterador = resultados.getIterator();
		while (iterador.hasMoreResources()) {
			Resource recurso = iterador.nextResource();
			String producto = (String) recurso.getContent();
			producto=producto.replace("<item>", "");
			producto=producto.replace("</item>", "");
			String lista[] = producto.split(",");
			productoObj =new Producto(Integer.parseInt(lista[0]),lista[1],Double.parseDouble(lista[2]),Integer.parseInt(lista[3]),Integer.parseInt(lista[4]),Integer.parseInt(lista[5]));

		}
		desconectar(coleccion);
		return productoObj;
	}

	public static void actualizar(Producto producto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("ColeccionPruebas");
		XPathQueryService servicio = 
				(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

		String sentenciaActualizarProducto = 
				"update replace " +
						"/productos/produc[cod_prod = " + producto.getCodigo() + "] with " +
						"<produc>" +
						"<cod_prod>" + producto.getCodigo() + "</cod_prod>" +
						"<denominacion>" + producto.getDenominacion() + "</denominacion>" +
						"<precio>" + String.format("%.2f", producto.getPrecio()) + "</precio>" +
						"<stock_actual>" + producto.getStockActual() + "</stock_actual>" +
						"<stock_minimo>" + producto.getStockMinimo() + "</stock_minimo>" +
						"<cod_zona>" + producto.getCodigoZona() + "</cod_zona>" +
						"</produc>";		
		servicio.query(sentenciaActualizarProducto);
	}

	public static void eliminar(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = conectar("ColeccionPruebas");
		
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String sentenciaEliminarProducto = 
					"update delete " +
							"/productos/produc[cod_prod = " + codigo + "]";
			servicio.query(sentenciaEliminarProducto);
		
	}

}
