import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modulo.Billete;

public class ConsultarBilletes {

	public static void main(String[] args) {
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select b from Billete b";
			TypedQuery<Billete> consulta = sesion.createQuery(sentenciaHQL);
			List<Billete> listaDepartamentos = consulta.getResultList();
			if (listaDepartamentos.size() == 0) {
				System.out.println("No hay billete en la base de datos.");
			}
			else {
				for (Billete departamento : listaDepartamentos) {
					System.out.println(departamento.toString());
				}
				System.out.println("Se han consultado " + listaDepartamentos.size() +
				                   " billetes de la base de datos.");
			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		HibernateUtil.closeSessionFactory();
	}

	
}