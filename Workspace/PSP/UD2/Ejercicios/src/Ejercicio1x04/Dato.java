package Ejercicio1x04;

public class Dato {
	
	private String cadena;
	private boolean disponible = false;
	
	/*
	 * true=Ping
	 * false=Pong
	 */
	
	
	
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
	
	public synchronized void set (String n, int turno){
		while(disponible || turno==1){
			try{
				wait();
			}
			catch(InterruptedException e){
	        	System.err.println(e.toString());
	        }
		}
		cadena = n;
		disponible = true;
		System.out.println(1 + " => Productor: " + 1 + ", produce: " + cadena);
		notifyAll();
	}
}
