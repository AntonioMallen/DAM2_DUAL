import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


/**
 * He quitado el comprobar del 9 ya que no acaba de funcionar y nose donde se queda en un bucle infinito.
 * 
 * @author Antonio Mallen
 *
 */
public class Ejercicio01 {
	public static void main (String []args) {

		Runtime runtime = Runtime.getRuntime();
		String line;

		try{

			Process hijo = runtime.exec("java Referencia", null ,  new File(".\\bin"));
			BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));// lee el resultado del hijo
			PrintStream ps= new PrintStream(hijo.getOutputStream()); // envia informacion al hijo

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // hace lo mismo que teclado(en el padre)

			System.out.println("Escribe un codigo: ");
			String linea = in.readLine(); // lectura de teclado en la clase padre
			String aux="";
			int nums1=0;
			while((linea.compareTo("0000")!=0)) {
				aux=""+linea.charAt(1)+linea.charAt(2);
				nums1=Integer.parseInt(aux);

				if(linea.charAt(0)=='A'||linea.charAt(0)=='B') {// comprobamos que el primer valor sea A o B
					if(nums1>=0&&nums1<=19) {// comprobamos que este entre el 0 y el 19
						//aux=""+linea.charAt(3);
						//if(Integer.parseInt(aux)=='9') { //comprobamos que el ultimo valor sea 9
							ps.println(linea); // esto se lo envia al hijo en su readLine(del hijo)
							ps.flush();
						/*}else {
							System.out.println("Codigo no valido");
						}*/
					}else {
						System.out.println("Codigo no valido");
					}
				}else {
					System.out.println("Codigo no valido");
				}
				System.out.println("Escribe otro codigo: ");
				linea = in.readLine(); // se hace de nuevo porque el otro esta fuera del bucle
			}
			
			if((line=br.readLine()) != null) {
				System.out.println(line); // lo recibe del ps.printLn(numero)(de parte del hijo)
			}
			hijo.waitFor();

			br.close();
			ps.close();
			in.close();
			hijo.destroy();

		}
		catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}