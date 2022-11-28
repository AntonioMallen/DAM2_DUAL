package Ejercicio1x06;

import java.util.concurrent.Semaphore;

public class Saludo extends Thread {
	int dato;
	Semaphore semaforo;

	Saludo(Semaphore semaforo, 
			int dato){
		this.dato = dato;
		this.semaforo = semaforo;
	}
	public void run() {
		try {
			semaforo.acquire();
			for(int i = 0; i<5; i++) {
				System.out.println(dato);
			}
			semaforo.release();
		} catch (InterruptedException e) { 
			System.out.println("ClaseSumar interrumpida");
		}
	}
}

