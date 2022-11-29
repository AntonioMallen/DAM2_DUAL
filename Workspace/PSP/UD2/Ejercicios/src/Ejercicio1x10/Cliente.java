package Ejercicio1x10;

import java.util.concurrent.Semaphore;

public class Cliente  extends Thread {
	int dato;
	Semaphore salir;
	Semaphore atender;
	/*
	 * Un sem para si le ateienden y otro para ver si puede salir
	 */
	Cliente(Semaphore atender,Semaphore salir, 
			int dato){
		this.dato=dato;
		this.salir = salir;
		this.atender=atender;
	}
	public void run() {
		
		try {	
			System.out.println("Cliente "+dato+" espera su turno");
			atender.release(); // entra un cliente
			
			System.out.println("Cliente "+dato+" es atendido");
			
			salir.acquire();// espera a poder salir
			System.out.println("Cliente "+dato+" sale de la tienda");
			
		}catch (InterruptedException e) { 
			System.out.println("ClaseSumar interrumpida");
		}
	}
}