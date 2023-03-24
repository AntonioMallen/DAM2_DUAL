package Ejercicio2;

import java.util.Random;

public class Coche extends Thread{

	private int id;
	private int tiempo;
	private Turno turno;
	
	Coche(int id, Turno turno){
		Random random = new Random();
		tiempo=random.nextInt(0,1500);
		this.id=id;
		this.turno=turno;
	}
	public void run() {
		try {
			
			System.out.println(id + " se pone a en cola");
			turno.entrada(id);
			System.out.println(id+" espera "+tiempo);
			Thread.sleep(tiempo);
			turno.salida();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
