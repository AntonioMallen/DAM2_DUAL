package Cesar;

public class main {
	static String sincifr="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	static String cifrado="DEFGHIJKLMNÑOPQRSTUVWXYZABC";
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
			salida+=cifrado.charAt(sincifr.indexOf(palabra.charAt(contador)));
		}
		return salida;
	}
	
	public static String descifrar(String palabra) {
		String salida="";
		for(int contador=0; contador<palabra.length();contador++){
			salida+=sincifr.charAt(cifrado.indexOf(palabra.charAt(contador)));
		}
		return salida;
	}

}
