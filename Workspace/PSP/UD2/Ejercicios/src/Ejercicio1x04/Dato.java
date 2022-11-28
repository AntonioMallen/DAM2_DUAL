package Ejercicio1x04;

public class Dato {

	private String cadena;
	private boolean disponible = false;
	private int turnAnt = 0;



	public synchronized void get(){
		while(!disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.toString());
			}
		}
		disponible = false;
		System.out.println(2 + " => Consumidor: 1, consume: " + cadena);
		notifyAll();
	}

	public synchronized void set (String n,int turno){
		while(disponible || turnAnt==turno){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.toString());
			}
		}
		turnAnt=turno;
		cadena = n;
		disponible = true;
		System.out.println(1 + " => Productor: " + 1 + ", produce: " + cadena);
		notifyAll();
	}
}
