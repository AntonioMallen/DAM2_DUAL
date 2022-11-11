package ud1x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

public class CalculaPrimos {

	public static boolean esPrimo(int numero) {
		  // El 0, 1 y 4 no son primos
		  if (numero == 0 || numero == 1 || numero == 4) {
		    return false;
		  }
		  for (int x = 2; x < numero / 2; x++) {
		    // Si es divisible por cualquiera de estos números, no
		    // es primo
		    if (numero % x == 0)
		      return false;
		  }
		  // Si no se pudo dividir por ninguno de los de arriba, sí es primo
		  return true;
		}
	
	public static void main(String[] args) {
		
		Random aleatorio= new Random();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		PrintStream ps= new PrintStream(System.out); // se comunica con la clase padre
		int numero;
		try {
			numero = Integer.parseInt(in.readLine());
			
			ps.println(true);
			ps.flush();
			if(esPrimo(numero)){
				ps.println("El numero "+numero+" es primo");
				ps.flush();
	
			}else {
				for(int cont = numero; cont>0;cont--) {
					if(esPrimo(cont)) {
						ps.println(cont);
						ps.flush();
					}
				}
				ps.println(1);
			}
			
			ps.println("false");
			ps.flush();
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
