package Ejercicio2;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ejercicio2 {

	public static void main(String[] args) {
		ArrayList<Coche> coches = new ArrayList<Coche>();
		
		int numeroPuestos=Integer.parseInt(args[0]);
		int numeroClientes=Integer.parseInt(args[1]);
		System.out.println("Hora de inicio es: "+LocalDateTime.now().toLocalTime());
		Turno turno = new Turno(numeroPuestos);
		
		/*
		 * Iniciamos todos los coches y los añadimos a la lista para hacer join,
		 * si hicieramos join directamente en este for no estariamos logrando nada
		 * ya que haria un hilo, luego otro...
		 */
		for(int i =1;i<=numeroClientes;i++) {
			Coche coche=new Coche(i, turno);
			coche.start();
			coches.add(coche);
		}
		/*
		 * Hacemos un for recorriendo los coches para que el mensaje de abajo solo
		 * se imprima una vez han terminado todos lo coches
		 */
		for(Coche coche: coches) {
			try {
				coche.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Hora de fin es: "+LocalDateTime.now().toLocalTime());
	}

}
