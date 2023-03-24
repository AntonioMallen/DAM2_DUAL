package Ejercicio1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Ejercicio1 {
	
	private static int NUMEROPACIENTES=3; // cantidad de pacientes que hay
	
	
	public static void main(String[] args) {

		/**
		 * Creamos dos semaforos, uno con la entrada de pacientes que sera
		 * atender y uno con la salida de pacientes que sera salir
		 */
		Semaphore atender= new Semaphore(0);
		Semaphore salir= new Semaphore(0);
		
		ArrayList<Paciente> pacientes =new ArrayList<Paciente>();
		
		/*
		 * Se crea el medico y se hace start
		 */
		Medico medico=new Medico(atender,salir);
		medico.start();
		
		/*
		 * Se crean pacientes segun el NUMEROPACIENTES seleccionado.
		 */
		for (int i =1;i<=NUMEROPACIENTES;i++) {
			Paciente paciente= new Paciente(i,atender,salir);
			paciente.start();
			pacientes.add(paciente); // se añade al ArrayList para hacer join posteriormente
			try {
				Thread.sleep(100); // esperara 0,1 segundos para el siguiente paciente
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Creamos un bucle for con un array, con el conseguiremos 
		 * que no quede ningun hilo sin ejecutar cuando hagamos el
		 * medico.interrupt
		 */
		for(Paciente paciente: pacientes) {
			try {
				paciente.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Tras asegurarnos de que todos los hilos han terminado,
		 * hacemos el interrupt de medico ya que no quedan mas pacientes.
		 * Asi evitaremos un bucle infinito
		 */
		medico.interrupt();

	}

}
