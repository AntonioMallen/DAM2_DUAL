package Polialfabeto;

public class main {


	static String sincifrar  = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	static String cifradoPar = "aeimptxbfjnquYcgkñrvzdhlosw";
	static String cifradoInPar="nrwbglpuzejñsXchmqvafkotydi";
	
	
	public static void main(String[] args) {
		String palabra="gsadgdsagsdsd".toUpperCase();
		System.out.println(palabra);
		String cifrado=cifrar(palabra);
		System.out.println(cifrado);
		System.out.println(descifrar(cifrado));

	}


	public static String cifrar(String palabra) {
		String salida="";
		for(int contador=0; contador<palabra.length();contador++){
			if(contador%2==0) {
				salida+=cifradoPar.charAt(sincifrar.indexOf(palabra.charAt(contador)));
			}else {
				salida+=cifradoInPar.charAt(sincifrar.indexOf(palabra.charAt(contador)));
			}
			
		}
		return salida;
	}
	
	public static String descifrar(String palabra) {
		String salida="";
		for(int contador=0; contador<palabra.length();contador++){
			if(contador%2==0) {
				salida+=sincifrar.charAt(cifradoPar.indexOf(palabra.charAt(contador)));
			}else {
				salida+=sincifrar.charAt(cifradoInPar.indexOf(palabra.charAt(contador)));
			}
		}
		return salida;
	}
}
