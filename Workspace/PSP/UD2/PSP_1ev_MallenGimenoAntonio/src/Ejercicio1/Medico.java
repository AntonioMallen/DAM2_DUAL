package Ejercicio1;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Medico  extends Thread {
	

	Semaphore atender;
	Semaphore salir;
	private int tiempoTotal=0;
	private static int []TIEMPO= {1000 , 1100 , 1500 , 2100 , 2500};

	Medico(Semaphore atender,Semaphore salir){
		this.atender = atender;
		this.salir = salir;
	}
	public void run() {
		try {
			/*
			 * Este bucle infinito sera interrumpido desde la clase main cuando no queden clientes
			 */
			while (true) {
				Random random = new Random();
				
				/*
				 * esta disponible para atender pacientes(deja entrar a un paciente)
				 */
				atender.release();
				
				int cant=random.nextInt(0,5); // sacamos el numero aleatorio
				int tiempo=TIEMPO[cant];
				Thread.sleep(tiempo);
				tiempoTotal+=tiempo;
				System.out.println("Tiempo de atencion: "+tiempo);
				
				/*
				 * Deja salir al paciente tras hacerle la consulta
				 */
				salir.release();

			}
			
		}catch (InterruptedException e) { // mensaje tras hacer interrupt
			System.out.println("Medico finaliza consulta. Tiempo total: "+tiempoTotal);
		}

	}
}