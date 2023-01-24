
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Departamento;
import modelo.Empleado;



public class AccesoFichaje {

	private final static String BASEDATOS= "data/fichajes.odb";

	/*
	 * inserta el departamento pasado por parametro
	 */
	public static void insertar(Departamento departamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;

		try {
			conexion = emf.createEntityManager();
			conexion.getTransaction().begin();
			conexion.persist(departamento);
			conexion.getTransaction().commit();
		}catch (Exception e) {
			throw e;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}

	}

	/*
	 * Te dice todos los empleados que hay en un departamento en concreto
	 */
	public static List<Empleado> consultarEmpleadosEnDepartamento(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Empleado> consulta = conexion.createQuery("select e from Empleado e,Departamento d "
					+ "where e.departamento.contains(d) and d.codigo="+codigo, 
					Empleado.class);
			List<Empleado> empleado = consulta.getResultList();
			return empleado;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}

	}

	/*
	 * Elimina el departamento con el codigo recibido por parametro
	 */
	public static void eliminar(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			Departamento departamento = conexion.find(Departamento.class, codigo);
			if(departamento!= null) {
				conexion.getTransaction().begin();
				conexion.remove(departamento);
				conexion.getTransaction().commit();
			}
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
	}

	/*
	 * Metodo usado para consultar si existen empleados con un id
	 */
	public static Empleado consultarEmpleado(String nombre) {
		Empleado empleado= null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			empleado = conexion.find(Empleado.class, nombre);
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
		return empleado;
	}

	/*
	 * Metodo usado para consultar si existen departamentos con un id
	 */
	public static Departamento consultarDepartamento(int id) {
		Departamento departamento= null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			departamento = conexion.find(Departamento.class, id);
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
		return departamento;
	}

/*
 * Este metodo recibe un nombre y un departamento, actualiza el departamento del empleado con
 * el nombre(para saber el empleado que actualizar) recibido por parametro.
 */
	public static void actualizar(String nombre, Departamento departamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;

		try {
			conexion = emf.createEntityManager();
			Empleado empleado = conexion.find(Empleado.class, nombre);
			conexion.getTransaction().begin();
			empleado.setDepartamento(departamento);
			conexion.getTransaction().commit();
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
	}


	/**
	 * Metodo auxiliar para consultar departamentos y comprobar los dos primeros apartados
	 */
	public static List<Departamento> consultar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(BASEDATOS);
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Departamento> consulta = conexion.createQuery("SELECT d FROM Departamento d", 
					Departamento.class);
			List<Departamento> departamentos = consulta.getResultList();
			return departamentos;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
			conexion.close();
		}

	}

}
