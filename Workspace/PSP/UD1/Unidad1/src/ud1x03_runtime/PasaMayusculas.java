package ud1x03_runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

public class PasaMayusculas {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		
		PrintStream ps= new PrintStream(System.out); // se comunica con la clase padre
		
		String linea;
		try {
			linea = in.readLine();
			while(linea!=null) {
				ps.println(linea.toUpperCase());
				ps.flush();
				linea=in.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
