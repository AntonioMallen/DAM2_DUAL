package Ejercicio1x06;
import java.util.concurrent.Semaphore;






public class EjemploSemaforo {

	public static void main(String[] args) throws InterruptedException {

		Semaphore semaforo= new Semaphore(1);
		Saludo hilo1 = new Saludo(semaforo, 125125125);
		Saludo hilo2 = new Saludo(semaforo, 456645564);

		hilo1.start();
		hilo1.join();
		hilo2.start(); 

	}

}