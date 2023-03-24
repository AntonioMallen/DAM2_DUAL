package Ejercicio1x03;


public class Consumidor extends Thread
	{
	    private Dato dato;

	 
	   
	    public Consumidor(Dato c) 
	    {
	        this.dato = c;

	    }
	 
	    public void run() 
	    {
	    	for (int i=0; i<25;i++){
	    		dato.get();
	    	}
	    }
	}