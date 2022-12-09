package Ejercicio1x08;

public class Main {

	public static void main(String[] args) {
		Barrera barrera = new Barrera(5);
		for(int i =1;i<=10;i++) {
			Coche coche=new Coche(i, barrera);
			coche.start();
		}
		
		
	}

}
