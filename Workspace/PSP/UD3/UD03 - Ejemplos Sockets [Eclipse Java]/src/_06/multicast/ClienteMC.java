package _06.multicast;

import java.net.*; 

public class ClienteMC { 
	
	public static void main(String args[]) throws Exception { 
		
		//Se crea el socket multicast 
		int puerto = 55555;//Puerto multicast 
		
		MulticastSocket ms = new MulticastSocket(puerto); 
		InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo 
		
		//Nos unimos al grupo 
		ms.joinGroup (grupo); 
		String msg=""; 
		byte[] buf = new byte[1000]; 
		 
		while(!msg.trim().equals("*")) { 
			
			//Recibe el paquete del servidor multicast 
			DatagramPacket paquete = new DatagramPacket(buf, buf.length); 
			ms.receive(paquete); 
			msg = new String(paquete.getData()); 
			System.out.println ("Recibo: " + msg.trim());
			
		}//Fin de while
		
		ms.leaveGroup(grupo); //abandonamos grupo
		
		//cierra socket
		ms.close();
		System.out.println("Socket cerrado...");
		
	
	}//Fin de main
				
}//Fin de ClienteMC