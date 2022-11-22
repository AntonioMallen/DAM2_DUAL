package Ejercicio1x03;

public class Compartido {
	
	private String cadena;
	private boolean disponible = false; //elemento vac�a
	
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
	
	public synchronized void set (String n){
		while(disponible){
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
