import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Ejercicio02 {

	public static void main (String []args) {


		String line;

		Runtime runtime = Runtime.getRuntime();
		try{

			Process hijo = runtime.exec("java MultiploDivisor", null ,  new File(".\\bin"));
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)

			System.out.println("Escribe un numero: ");
			int num1 = Integer.parseInt(in.readLine());
			System.out.println("Escribe un numero: ");
			int num2 = Integer.parseInt(in.readLine());

			ps.println(num1); // esto se lo envia al hijo en su readLine(del hijo)
			ps.flush();
			ps.println(num2); // esto se lo envia al hijo en su readLine(del hijo)
			ps.flush();
			
			if((line=br.readLine()) != null) {
				System.out.println(line); // lo recibe del ps.printLn(numero)(de parte del hijo)
			}


			hijo.destroy();

		}
		catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}catch(NumberFormatException e) {
			System.err.println("El dato no es correcto");
		}



	}
}
