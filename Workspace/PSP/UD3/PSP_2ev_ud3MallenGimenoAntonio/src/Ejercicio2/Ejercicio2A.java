package Ejercicio2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import entrada.Teclado;

public class Ejercicio2A {
	/**
	 * Hara de cliente
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String cadena="";
			do {
				cadena="";
				// Flujo de entrada estándar
				BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
				DatagramSocket clientSocket = new DatagramSocket(50000);  
				byte[] enviados = new byte[1024]; 
				byte[] recibidos = new byte[1024]; 
				boolean correcto=true;
				// Datos del servidor
				InetAddress IPServidor = InetAddress.getLocalHost();
				int puerto = 55002; // puerto por el que escucha 

				String clave = Teclado.leerCadena("Indica clave: ");

				if(clave.length()>=8){
					for(int contador =0; contador<clave.length() && correcto==true;contador++) {
						if(clave.charAt(contador)>='0' && clave.charAt(contador)<='9') {
							cadena+=clave.charAt(contador);
						}else {
							cadena="Clave no valida";
							correcto=false;
						}
					}
				}else {
					cadena = "Clave no valida";
					correcto=false;
				}

				if(correcto==true){
					enviados = cadena.getBytes(); 
					// Se envía el datagrama al servidor
					DatagramPacket envio = new DatagramPacket (enviados, enviados.length, IPServidor, puerto); 
					clientSocket.send(envio);


					// Se recibe el datagrama del servidor
					DatagramPacket recibo = new DatagramPacket (recibidos, recibidos.length); 
					clientSocket.receive(recibo);

					String respuesta = new String(recibo.getData()).trim(); 
					System.out.println(respuesta);
				}else {
					System.out.println(cadena);
				}
				clientSocket.close();
			}while(cadena.equals("Clave no valida"));
		}catch(IOException e) {
			e.printStackTrace();
		}

	}//Fin de main

}
