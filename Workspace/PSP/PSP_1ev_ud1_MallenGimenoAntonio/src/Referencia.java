import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Referencia {


	public static void main(String[] args) {
		int REF01=0;
		int REF02=0;
		int REF03=0;
		int REF09=0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 

		PrintStream ps= new PrintStream(System.out); // se comunica con la clase padre
		String aux="0";
		int nums1=0;
		String linea;
		try {
			linea = in.readLine();
			while(linea!=null && nums1!=-1) {
				
				if(!(linea.equals("0000"))) {
					aux=""+linea.charAt(1)+linea.charAt(2);
					nums1=Integer.parseInt(aux);
				}
				
				if(linea.charAt(0)=='A') {
					if(nums1>=0&&nums1<=10){
						REF01+=1;
					}else{
						REF03+=1;
					}

				}else {
					if(nums1>=0&&nums1<=10 ) {
	
						REF02+=1;
					}else {
						REF09+=1;
					}
				}
				linea=in.readLine();
			}

			String resultado= "Codigos con REF01: "+REF01+"\n"+
					"Codigos con REF02: "+REF02+"\n"+
					"Codigos con REF03: "+REF03+"\n"+
					"Codigos con REF09: "+REF09+"";
			ps.println(resultado);
			ps.flush();
			
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
