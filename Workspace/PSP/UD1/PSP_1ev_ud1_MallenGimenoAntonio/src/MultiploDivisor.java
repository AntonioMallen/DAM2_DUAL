import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

public class MultiploDivisor {

	public static void main(String[] args) {

		Random aleatorio= new Random();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // entrada (en este caso generica)
		PrintStream ps= new PrintStream(System.out); // salida
		int num;
		int num1;
		String result="";
		try {
			num= Integer.parseInt(in.readLine());
			num1 = Integer.parseInt(in.readLine());
			

			if(num%num1==0) {
				result="Multiplo";
			}else if(num1%num==0) { // este no me queda muy claro que es lo que pide
				result="Divisor";
			}else {
				System.out.println("Esto han sido 0,25");
			}

			ps.println(result);
			ps.flush();



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}