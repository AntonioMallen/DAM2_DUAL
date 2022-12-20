package _04.udp.ejemplo1;

import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 

public class ServidorUDP { 
	
	public static void main(String[] argv) throws Exception { 
		
		byte[] bufer = new byte[1024];//bufer para recibir el datagrama 
		
		//Asociación del socket al puerto 50000 
		DatagramSocket socket = new DatagramSocket(50000); 
		
		//ESPERANDO DATAGRAMA 
		System.out.println("Esperando Datagrama ................"); 
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length); 
		socket.receive(recibo);
		int bytesRec = recibo.getLength();//se obtiene cantidad de bytes    
		String paquete= new String(recibo.getData());//paso a String 
		
		//Se visualiza la información
		System.out.println("Número de Bytes recibidos: " + bytesRec); 
		System.out.println("Contenido del Paquete    : " + paquete.trim()); 
		System.out.println("Puerto origen del mensaje: " + recibo.getPort());
		System.out.println("IP de origen             : " + recibo.getAddress().getHostAddress());
		System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());
		
		socket.close(); 
		
	}//Fin de main
	
}// Fin de SerivdorUDP