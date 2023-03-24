package solucion;

public class Compartido {
	private int numero;
	private boolean disponible = false; //elemento vac�a
	
	public synchronized int get(){
		while(!disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
	        	System.err.println(e.toString());
	        }
		}
		disponible = false;
		System.out.println(2 + " => Consumidor: " + 2 + ", consume: " + numero);
		notifyAll();
		return numero;
		 
	}
	
	public synchronized void set (int n){
		while(disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
	        	System.err.println(e.toString());
	        }
			
		}
		numero = n;
		disponible = true;
		System.out.println(1 + " => Productor: " + 1 + ", produce: " + numero);
		notifyAll();
	}
}
