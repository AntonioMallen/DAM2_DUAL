package transposicionColumnarClave;

import java.util.Arrays;

public class main {

	static String[][] tabla;
	static int num_columnas=0;
	static int num_filas=0;
	static String clave="clave".toUpperCase();
	
	public static void main(String[] args) {

		String palabra=clave+"EJEMPLODETRANSPOSICIONCOLUMNARd".toUpperCase();
		
		
		num_columnas=clave.length();
		double resultado = Math.ceil(((double)(palabra.length())/num_columnas));
	
		num_filas=(int)resultado;
		tabla=new String[num_filas][num_columnas];
		
		
		System.out.println(palabra);
		String cifrado=cifrar(palabra);
		System.out.println(cifrado);
		//System.out.println(descifrar(cifrado));
	}



	public static String cifrar(String palabra) {
		String salida="";
		String [] zonas = new String[num_columnas];
		int contador=0;
		for(int i =0;i<num_filas && contador!= palabra.length();i++) {
			
			for(int j =0;j<num_columnas && contador!= palabra.length();j++) {
				tabla[i][j]=palabra.charAt(contador)+"";
				contador++;
				//System.out.println(tabla[i][j]);
			}
		}
		contador=0;
		String aux="";
		for(int i =0;i<num_columnas && contador!= palabra.length();i++) {
			
			for(int j =0;j<num_filas && contador!= palabra.length();j++) {
				aux+=tabla[j][i];
				contador++;
			}
			zonas[i]=aux;
			aux="";
		}
		
		for(String test: zonas) {
			System.out.println(test);
		}
		Arrays.sort(zonas);
		System.out.println();
		for(String test: zonas) {
			System.out.println(test);
		}
		return salida;
	}
	
}
