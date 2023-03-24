package Ejercicio1x10;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Frutero  extends Thread {
	int dato;
	Semaphore atender;
	Semaphore salir;
	int dinero=0;
	/*
	 * total de caja que hace, tiempo
	 * usara mismos
	 */

	Frutero(Semaphore atender,Semaphore salir, int dato){
		this.atender = atender;
		this.salir = salir;
	}
	public void run() {
		try {
			while (true) { // trabaja indefinidamente

				Random r = new Random();
				atender.acquire();// espera a tener un cliente
				Thread.sleep(100);
				int cobra=r.nextInt(0,10);
				dinero +=cobra;
				salir.release();// saca a un cliente
				System.out.println("He cobrado "+cobra);
			}
			
		}catch (InterruptedException e) { 
			System.out.println("ClaseSumar interrumpida");
			System.out.println("Hoy he cobrado "+dinero);
		}

	}
}