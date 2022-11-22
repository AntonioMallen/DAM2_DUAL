package Ejercicio1x02;

public class Ejercicio2 {

	public static void main(String [] args) { 
		TIC hilo1 = new TIC(10);
		TAC hilo2 = new TAC(10);
		hilo1.start();
		hilo2.start();
		System.out.println("Fin del programa principal");
	}

}

class TIC extends Thread{
	private int n;


	TIC(int n){
		this.n=n;
	}

	@Override
	public void run() {
		for (int i =0; i<100 ; i++){
			try {
				sleep(n);
				System.out.println("TIC");
			} catch (InterruptedException e) {
				System.out.println("He sido interrumpido (TIC)");
			}
		}
	}
}
class TAC extends Thread{
	private int n;


	TAC(int n){
		this.n=n;
	}

	@Override
	public void run() {
		for (int i =0; i<100 ; i++){
			try {
				sleep(n);
				System.out.println("TAC");
			} catch (InterruptedException e) {
				System.out.println("He sido interrumpido (TAC)");
			}
		}
	}

}