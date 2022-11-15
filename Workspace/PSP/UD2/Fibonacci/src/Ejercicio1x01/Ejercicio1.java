package Ejercicio1x01;

public class Ejercicio1 {

	public static void main(String [] args) { 
		OtroHilo hilo1 = new OtroHilo(21);
		OtroHilo hilo2 = new OtroHilo(21);
		hilo1.start();
		try {
			hilo1.join();
			hilo2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fin del programa principal");
	}

}

class OtroHilo extends Thread{
	private int n;
	

	OtroHilo(int n){
			this.n=n;
	}

	@Override
	public void run() {
		int a=1;
		int b=1;
		int c;
		System.out.println(a);
		System.out.println(b);
		for (int i =0; i<n ; i++){
			c=a+b;
			System.out.println(c+" ");
			a=b;
			b=c;
		}
	}

}