import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modulo.Billete;
import modulo.Estacion;
import modulo.Viajero;
import modulo.Clase;

public class AccesoBaseDatos {


	public static void insertar(Billete billete) {
		Session sesion = null;
		Transaction transaccion = null;
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			sesion.save(billete);
			transaccion.commit();
			System.out.println("Se ha insertado un billete en la base de datos.");
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			System.out.println("Error de MySQL o Hibernate: " + e.getMessage());
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

	public static Estacion elegirEstacion(int codigo) {
		Estacion estacion = null;
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Estacion e";
			TypedQuery<Estacion> consulta = sesion.createQuery(sentenciaHQL);
			List<Estacion> listaEstaciones = consulta.getResultList();
			if (listaEstaciones.size() == 0) {
				System.out.println("No hay Estaciones con ese codigo en la base de datos.");
			}
			else {
				for (Estacion recorrerEstacion : listaEstaciones) {
					if(recorrerEstacion.getCodigo()==codigo) {
						estacion=recorrerEstacion;
					}
				}

			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return estacion;
	}


	public static ArrayList<Viajero> imprimirViajero() {
		ArrayList<Viajero> viajeros=new ArrayList<Viajero>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Viajero e";
			TypedQuery<Viajero> consulta = sesion.createQuery(sentenciaHQL);
			List<Viajero> listaViajeros = consulta.getResultList();
			if (viajeros.size() == 0) {
				System.out.println("No hay estaciones en la base de datos.");
			}
			else {
				for (Viajero viajero : viajeros) {
					viajeros.add(viajero);
				}

			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return viajeros;
	}

	public static ArrayList<Estacion> imprimirEstacion() {
		ArrayList<Estacion> estaciones=new ArrayList<Estacion>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Estacion e";
			TypedQuery<Estacion> consulta = sesion.createQuery(sentenciaHQL);
			List<Estacion> listaEstaciones = consulta.getResultList();

			for (Estacion estacion1 : listaEstaciones) {
				estaciones.add(estacion1);
			}

		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return estaciones;
	}
	/*public static List<Billete> consultar() {
		TypedQuery<Billete> consulta=null;
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Billete e";
			consulta = sesion.createQuery(sentenciaHQL);

			return consulta.getResultList();
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}

	}*/
	public static ArrayList<Billete> consultar(){
		ArrayList<Billete> billetes=new ArrayList<Billete>();
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select b from Billete b";
			TypedQuery<Billete> consulta = sesion.createQuery(sentenciaHQL, Billete.class);
			billetes = (ArrayList<Billete>) consulta.getResultList();

		}
		finally {
			if (sesion != null) {
				sesion.close();
			}

		}

		return billetes;
	}

	public static void borrar(int codigo) {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();	
			Billete billete = sesion.get(Billete.class, (short) codigo);
			if (billete == null) {
				System.out.println("No se ha encontrado ningún billete con código " + codigo);
			}
			else {
				sesion.delete(billete);
				transaccion.commit();
				System.out.println("Se ha eliminado un billete de la base de datos.");
			}
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			System.out.println("Error de MySQL o Hibernate: " + e.getMessage());
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}


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

    public static void borrarClase(Clase clase) {
        Session sesion = null;
        Transaction transaccion = null;
        try {
            SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
            sesion = fabricaSesiones.openSession();
            transaccion = sesion.beginTransaction();
            for (Object viajero : clase.getViajeros()) {
                for(Object billete :((Viajero)viajero).getBilletes()) {
                    sesion.delete((Billete)billete);
                }
                sesion.delete((Viajero)viajero);
            }

            sesion.delete(clase);
            transaccion.commit();
            System.out.println("Se ha eliminado un clase de la base de datos.");
        }
        catch (Exception e) {
            if (transaccion != null) {
                transaccion.rollback();
            }
            System.out.println("Error de MySQL o Hibernate: " + e.getMessage());
        }
        finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

}
