package Ejercicio1x05;

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

			if(dato==1) {
				semaforo.acquire();// se le resta uno al sem y si es 0 se queda esperando
			}
			System.out.println("Hola, soy el hilo "+ dato);
			if(dato==2) {
				semaforo.release();// añade uno a la lista del semaforo(empieza en 0 y se le suma 1)
			}

		}catch (InterruptedException e) { 
			System.out.println("ClaseSumar interrumpida");
		}
	}
}


