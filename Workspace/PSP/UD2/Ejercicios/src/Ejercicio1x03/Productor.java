package Ejercicio1x03;
public class Productor extends Thread
{
    private Compartido dato;
    private int num;
 
   
    public Productor(Compartido c, int n) 
    {
        this.dato = c;
        this.num = n;
    }
 
    public void run() 
    {
    	for (int i=0; i<25;i++){
    		dato.set("Ping");
    		dato.set("Pong");
    	}
        try{
        	sleep(100);
        }
        catch(InterruptedException e){
        	System.err.println(e.toString());
        }
    }
}