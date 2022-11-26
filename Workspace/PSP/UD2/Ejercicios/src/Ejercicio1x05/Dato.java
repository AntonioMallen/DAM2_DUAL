package Ejercicio1x05;

public class Dato {

	private String cadena;
	private boolean disponible = false;
	private int turnSig = 1;



	public synchronized String get(int num){
		while(!disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.toString());
			}
		}
		disponible = false;
		System.out.println(2 + " => Consumidor: " + num + ", consume: " + cadena);
		notifyAll();
		return cadena;

	}

	public synchronized void set (String n,int turno){
		while(disponible || turnSig!=turno){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.toString());
			}
		}
		if(turno==7) {
			turnSig=1;
		}else {
			turnSig=turno+1;
		}
		cadena = n;
		disponible = true;
		System.out.println(1 + " => Productor: " + 1 + ", produce: " + cadena);
		notifyAll();
	}
}
