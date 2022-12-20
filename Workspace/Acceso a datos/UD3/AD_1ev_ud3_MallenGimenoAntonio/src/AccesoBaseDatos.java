import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modulo.Estacion;
import modulo.Viajero;
import modulo.Clase;

public class AccesoBaseDatos {


	/*
	 * Ej1
	 */



	/*
	 * Este metodo crea un arraylist, lo rellena de Estaciones
	 * extraidas de la base de datos y hace un return de este array
	 *  para imprimirlo en la clase main
	 */
	public static ArrayList<Estacion> consultarEstacion(){
		ArrayList<Estacion> estaciones=new ArrayList<Estacion>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Estacion e Order by e.agnoInauguracion DESC";
			TypedQuery<Estacion> consulta = sesion.createQuery(sentenciaHQL, Estacion.class);
			estaciones = (ArrayList<Estacion>) consulta.getResultList();
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}

		}
		return estaciones;
	}


	/*
	 * Ej2
	 */



	/*
	 * Este metodo hace un select de Clase en la base de datos 
	 * y lo introduce todo en un array, luego hace un return
	 */

	public static ArrayList<Clase> imprimirClase() {
		List<Clase> listaClases=null;
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select c from Clase c";
			TypedQuery<Clase> consulta = sesion.createQuery(sentenciaHQL);
			listaClases= consulta.getResultList();
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return (ArrayList<Clase>) listaClases;
	}


	/*
	 * Este metodo a traves de un codigo pasado por parametro,
	 * buscara la Clase con ese mismo codigo y la devolvera con un
	 * return, en caso de no existir una clase devolvera un null 
	 * (con esto comprobaremos en la clase Main si hay 
	 * alguno con el codigo especifico)
	 */
	public static Clase elegirClase(int codigo) {
		Clase clase = null;
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			clase = sesion.get(Clase.class, (short) codigo);

		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return clase;
	}


	/*
	 * Este metodo trae un viajero por paremetros y lo inserta
	 * dentro de la base de datos.
	 */

	public static void insertar(Viajero viajero) {
		Session sesion = null;
		Transaction transaccion = null;
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			sesion.save(viajero);
			transaccion.commit();
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}



	/*
	 * Ej3
	 * 
	 */


	/*
	 * Este metodo comprueba si existe una Clase, a traves
	 * de un codigo recibido por parametros y en caso de que exista
	 * la devolver por return, en caso de que no exista devolvera un
	 * null
	 */
	public static Clase consultarClase(int codigo) {
		Clase clase = null;
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			clase = sesion.get(Clase.class, (short) codigo);
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return clase;
	}

	/*
	 * Este metodo recibe una Clase por parametro, primero
	 * comprobara si esta clase tiene viajeros, en caso de que exista alguno
	 * no borrara la clase sino que devolvera una lista de viajeros
	 */

	public static Set<Viajero> borrarClase(Clase clase) {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			Set<Viajero> viajeros=  clase.getViajeros();
			if(viajeros.size()==0) {
				sesion.delete(clase);
			}else {
				return viajeros;
			}

			transaccion.commit();

		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return null;
	}


	/*
	 * Testeo de eliminar
	 * 
	 * Este metodo lo he creado especificamente para comprobar
	 * borrarClase(), con el creo una clase la cual no tenga ningun
	 * viajero asociado( por lo cual podra ser eliminada )
	 */
	public static void insertarClase() {
		Session sesion = null;
		Transaction transaccion = null;
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			Clase clase1 = new Clase("a", new BigDecimal(2.3), (short) 2);
			sesion.save(clase1);
			transaccion.commit();
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

}
