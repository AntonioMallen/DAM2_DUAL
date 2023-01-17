package _04.udp.ejemplo1;

import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 

public class ClienteUDP { 
	
	public static void main(String[] argv) throws Exception { 
		
		InetAddress destino = InetAddress.getLocalHost(); 
		int port = 50000; //puerto 
		byte[] mensaje = new byte[1024]; 
		
		String saludo="Enviando Saludos !!";
		mensaje = saludo.getBytes(); // String a bytes 
		
		//Se construye el datagrama a enviar
		DatagramPacket envio = new DatagramPacket (mensaje, mensaje.length, destino, port); 
		DatagramSocket socket = new DatagramSocket(5);//Puerto local 
		System.out.println("Enviando Datagrama de longitud: "+ mensaje.length);
		System.out.println("Host destino : "+ destino.getHostName()); 
		System.out.println("IP Destino : " + destino.getHostAddress()); 
		System.out.println("Puerto local del socket: " + socket.getLocalPort()); 
		System.out.println("Puerto al que envio: " + envio.getPort()); 
		
		//Se envía el datagrama
		socket.send(envio); 
		socket.close(); 
	
	}//Fin de main 
	
}//Fin de ClienteUDP
