package Ejercicio1x10;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		Cliente c = null ;
		Semaphore atender= new Semaphore(0);
		Semaphore salir= new Semaphore(0);
		ArrayList<Cliente> clientes =new ArrayList<Cliente>();
		Frutero f1=new Frutero(atender,salir,1);
		f1.start();
		
		for (int i =0;i<2;i++) {
			c= new Cliente(atender,salir,i);
			clientes.add(c);
			c.start();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Cliente cliente: clientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		f1.interrupt();

	}

}
