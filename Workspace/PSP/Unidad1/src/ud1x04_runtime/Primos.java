package ud1x04_runtime;
import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Primos {

	public static void main (String []args) {

		String line;
		Runtime runtime = Runtime.getRuntime();

		try {
			Process hijo = runtime.exec("java ud1x04_runtime/CalculaPrimos", null ,  new File(".\\bin"));
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)
			try {

				System.out.println("Escribe un numero entero: ");
				int numero = Integer.parseInt(in.readLine());

				ps.println(numero); // esto se lo envia al hijo en su readLine(del hijo)
				ps.flush();
				line=br.readLine();
				while(line!=null) {
					System.out.println(line);
					line=br.readLine();
				}


			}finally {
				in.close();
				br.close();
				ps.close();
				hijo.destroy();
			}
		}catch(NumberFormatException e) {
			System.err.println("Dato introducido no válido");
		}catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}
	}
}
