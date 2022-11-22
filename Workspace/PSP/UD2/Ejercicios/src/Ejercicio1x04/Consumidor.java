package Ejercicio1x04;


public class Consumidor extends Thread
	{
	    private Dato dato;
	    private int num;
	 
	   
	    public Consumidor(Dato c, int n) 
	    {
	        this.dato = c;
	        this.num = n;
	    }
	 
	    public void run() 
	    {
	    	String valor = "";
	    	for (int i=0; i<50;i++){
	    		valor = dato.get(num);
	    	}
	    }
	}