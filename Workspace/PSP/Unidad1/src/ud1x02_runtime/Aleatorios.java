package ud1x02_runtime;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import entrada.Teclado;

public class Aleatorios {
	
	public static void main (String []args) {
		
		// recibir dato usuario
		// hacer un do while not equals "fin"
		// y luego meter todo lo de abajo
		
		
		
		String line;
	
		Runtime runtime = Runtime.getRuntime();
		try{

			Process hijo = runtime.exec("java ud1x02_runtime/GeneraAleatorio", null ,  new File(".\\bin"));
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)

			System.out.println("Escribe una linea: ");
			String linea = in.readLine(); // esto lo lee del teclado en la clase padre
			while((linea.compareTo("fin")!=0)) {
				
				ps.println(""); // esto se lo envia al hijo en su readLine(del hijo)
				ps.flush();
				
				if((line=br.readLine()) != null) {
					System.out.println(line); // lo recibe del ps.printLn(numero)(de parte del hijo)
				}

				System.out.println("Escribe una linea: ");
				linea = in.readLine(); // se hace de nuevo porque el otro esta fuera del bucle
			}
			hijo.destroy();
		
		}
		catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}
		
		
		
	}
}
