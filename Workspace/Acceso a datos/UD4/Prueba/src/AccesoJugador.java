import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Jugador;

public class AccesoJugador {

	public static void insertar(Jugador jugador) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;

		try {
			conexion = emf.createEntityManager();

			conexion.persist(jugador);
		}catch (Exception e) {
			throw e;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}
	
	public static List<Jugador> consultar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Jugador> consulta = conexion.createQuery("SELECT j FROM Jugador j", 
			                                                    Jugador.class);
			List<Jugador> jugadores = consulta.getResultList();
			return jugadores;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
	
	}
	
	public static Jugador consultarCodigo(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		Jugador jugador=null;
		try {
			conexion = emf.createEntityManager();
			jugador = conexion.find(Jugador.class, codigo);
			return jugador;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			emf.close();
		}
	
	}
	
	public static void actualizar(Jugador jugador) {
		
	}
	
}
