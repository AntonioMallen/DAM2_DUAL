package Ejercicio1x08;

import java.util.Random;

public class Coche extends Thread{
/*
 	Cosa que entra.
 	numero de plaza = posicion del vector
 	
 	recurso compartido= barrera
 	
 	
 */
	private int id;
	private Barrera barrera;
	
	Coche(int id, Barrera barrera){
		this.id=id;
		this.barrera=barrera;
	}
	public void run() {
		try {
			Random random = new Random();
			Thread.sleep(random.nextInt(100,1000));
			System.out.println("Coche " + id + " intenta entrar");
			int plaza = barrera.entrada(id);
			System.out.println("Coche " + id + " aparca en la plaza " + plaza);
			barrera.mostrar();
			Thread.sleep(random.nextInt(100,1000));
			barrera.salida(plaza);
			System.out.println("Coche " + id + " ha salido");
			barrera.mostrar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
