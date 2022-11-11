package ud1x04;
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
		ProcessBuilder pb = new ProcessBuilder("java", "ud1x04/CalculaPrimos");
		pb.directory(directorio);
		Process hijo;
		try{
			hijo= pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)

			System.out.println("Escribe un numero entero: ");
			int numero = Integer.parseInt(in.readLine());

			ps.println(numero); // esto se lo envia al hijo en su readLine(del hijo)
			ps.flush();
			boolean sigo=Boolean.parseBoolean(br.readLine());

			while(sigo) {
				line=br.readLine();


				if(line.charAt(0)>'9' || line.charAt(0)<'0') {
					if(line.equals("false")){
						sigo=false;
					}else {
						System.out.println(line);
					}
				}else {
					if(line != null) {
						System.out.println(line); // lo recibe del ps.printLn(numero)(de parte del hijo)
					}
				}
			}
			hijo.destroy();

		}catch(NumberFormatException e) {
			System.err.println("Dato introducido no válido");
		}catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}
	}
}
