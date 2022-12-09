package Ejercicio1;

import java.util.concurrent.Semaphore;

public class Paciente  extends Thread {
	private int id;
	private Semaphore terminar;
	private Semaphore atender;
	
	Paciente(int id, Semaphore atender,Semaphore terminar){
		this.id=id;
		this.terminar = terminar;
		this.atender=atender;
	}
	public void run() {
		
		try {	
			System.out.println("Paciente "+id+" espera su turno");
			atender.acquire(); // entra un paciente
			
			System.out.println("Paciente "+id+" entra en consulta");
			
			terminar.acquire();// espera a terminar la consulta
			System.out.println("Paciente "+id+" sale de la consulta del médico");
			
		}catch (InterruptedException e) { 
			System.out.println("ClasePaciente interrumpida");
		}
	}
}