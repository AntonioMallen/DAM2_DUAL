package acceso;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Departamento;
import modelo.Empleado;

public class AccesoDatos {

	public static void insertar(Departamento dep) {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			sesion.save(dep);
			transaccion.commit();
			System.out.println("Se ha insertado un departamento en la base de datos.");
		}catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

	public static List<Departamento> consultarTodos() {
		Session sesion = null;
		List<Departamento> listaDepartamentos=null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select d from Departamento d";
			TypedQuery<Departamento> consulta = sesion.createQuery(sentenciaHQL);
			listaDepartamentos = consulta.getResultList();
			for (Departamento departamento : listaDepartamentos) {
				System.out.println(departamento.toString());
			}

		}finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		HibernateUtil.closeSessionFactory();
		return listaDepartamentos;
	}

}
