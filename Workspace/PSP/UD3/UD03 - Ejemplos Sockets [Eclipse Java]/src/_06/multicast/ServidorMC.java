package _06.multicast;

import java.io.*; 
import java.net.*; 

public class ServidorMC { 
	
	public static void main(String args[]) throws Exception { 
		
		// Flujo para entrada estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		
		//Se crea el socket multicast. 
		MulticastSocket ms = new MulticastSocket(); 
		int puerto = 55555;//Puerto multicast 
		InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo 
		String cadena="";
		 
		while(!cadena.trim().equals("*")) { 
			
			System.out.print("Datos a enviar al grupo: "); 
			cadena = in.readLine(); 
			
			// Se envía al grupo
			DatagramPacket paquete = new DatagramPacket (cadena.getBytes(), cadena.length(), grupo, puerto); 
			ms.send (paquete);
			
		}//Fin de while
		
		//cierro socket
		ms.close (); 
		System.out.println ("Socket cerrado..."); 
		
	}//Fin de main
	
}//Fin de ServidorMC
