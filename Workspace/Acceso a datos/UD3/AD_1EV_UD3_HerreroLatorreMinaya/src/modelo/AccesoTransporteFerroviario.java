package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AccesoTransporteFerroviario {

	public static ArrayList<Estacion> consultarEstacion() {
		ArrayList<Estacion> estaciones=new ArrayList<Estacion>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Estacion e ORDER BY e.agnoInauguracion desc";
			TypedQuery<Estacion> consulta = sesion.createQuery(sentenciaHQL);
			List<Estacion> listaEstaciones = consulta.getResultList();

			for (Estacion estacion : listaEstaciones) {
				estaciones.add(estacion);
			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return estaciones;
	}
	
	public static ArrayList<Clase> consultarClases() {
		ArrayList<Clase> clase=new ArrayList<Clase>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select c from Clase c";
			TypedQuery<Clase> consulta = sesion.createQuery(sentenciaHQL);
			List<Clase> listaClases = consulta.getResultList();

			for (Clase cla : listaClases) {
				clase.add(cla);
			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return clase;
	}
	
	public static ArrayList<Clase> consultarClase(int codigo){
		ArrayList<Clase> viajeros=new ArrayList<Clase>();
		Session sesion = null;
		try {
			consultarClases();
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select c from Clase c where c.codigo="+codigo;
			TypedQuery<Clase> consulta = sesion.createQuery(sentenciaHQL, Clase.class);
			viajeros = (ArrayList<Clase>) consulta.getResultList();

			for(Clase v: viajeros) {
				System.out.println(v);
			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}

		}
		return viajeros;
	}
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
	
	public static boolean borrarClase(Clase clase) {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			if(clase.getViajeros()!=null) { // si no hay viajeros borra y devuelve true
				sesion.delete(clase);
				transaccion.commit();
				return true;
			}
			transaccion.commit();
			return false;
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
	public static Clase consultarClases(int codigo) {
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

}
