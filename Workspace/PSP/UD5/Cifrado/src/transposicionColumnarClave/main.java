package transposicionColumnarClave;

public class main {

	static String[][] tabla;
	static int num_columnas=0;
	static int num_filas=0;
	static String clave="clave".toUpperCase();
	
	public static void main(String[] args) {

		String palabra=clave+"EJEMPLODETRANSPOSICIONCOLUMNAR".toUpperCase();
		
		
		num_columnas=clave.length();
		num_filas=(palabra.length()/num_columnas)+1;
		tabla=new String[num_filas][num_columnas];
		
		
		System.out.println(palabra);
		String cifrado=cifrar(palabra);
		System.out.println(cifrado);
		//System.out.println(descifrar(cifrado));
	}



	public static String cifrar(String palabra) {
		String salida="";
		int contador=0;
		for(int i =0;i<num_filas && contador!= palabra.length();i++) {
			System.out.println("-");
			for(int j =0;j<num_columnas && contador!= palabra.length();j++) {
				tabla[i][j]=palabra.charAt(contador)+"";
				contador++;
				System.out.println(tabla[i][j]);
			}
		}
		contador=0;
		for(int i =0;i<num_columnas && contador!= palabra.length();i++) {
			System.out.println("-");
			for(int j =0;j<num_filas && contador!= palabra.length();j++) {
				salida+=tabla[j][i];
				//System.out.println(salida);
				contador++;
			}
		}
		String aux=salida.replace("null", "");
		System.out.println(aux);
		return aux;
	}
	
}