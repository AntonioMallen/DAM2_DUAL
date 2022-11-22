package Ejercicio1x03;


public class Consumidor extends Thread
	{
	    private Compartido dato;
	    private int num;
	 
	   
	    public Consumidor(Compartido c, int n) 
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