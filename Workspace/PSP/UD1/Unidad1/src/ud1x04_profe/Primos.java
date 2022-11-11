package ud1x04_profe;
import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Primos {

	public static void main (String []args) {

		String line;
		File directorio = new File(".\\bin"); //Se indica el directorio en el que está
		ProcessBuilder pb = new ProcessBuilder("java", "ud1x04_profe/CalculaPrimos");
		pb.directory(directorio);
		Process hijo;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)

		try{
			hijo= pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo

			System.out.println("Escribe un numero entero: ");
			int numero = Integer.parseInt(in.readLine());

			ps.println(numero); // esto se lo envia al hijo en su readLine(del hijo)
			ps.flush();
			line=br.readLine();
			while(line!=null) {
				System.out.println(line);
				line=br.readLine();
			}

			br.close();
			ps.close();
			hijo.destroy();
		}catch(NumberFormatException e) {
			System.err.println("Dato introducido no válido");
		}catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}finally {
			try {
				in.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
