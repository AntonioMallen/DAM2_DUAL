package Ejercicio2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejercicio2B {
	/**
	 * Hara de servidor
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket = new DatagramSocket(55002);
			while(true) {
				byte[] recibidos = new byte[1024]; 
				byte[] enviados = new byte[1024]; 
				String cadenaRecibida;
				String aux="";
				recibidos = new byte[1024]; 
				DatagramPacket paqRecibido = new DatagramPacket (recibidos, recibidos.length);

				serverSocket.receive(paqRecibido);

				cadenaRecibida = new String(paqRecibido.getData()); 

				InetAddress IPOrigen = paqRecibido.getAddress();

				int puerto = paqRecibido.getPort(); 
				String clave = cadenaRecibida.trim(); 



				
				for(int contador = clave.length()-1; contador>=0;contador--) {
					aux += clave.charAt(contador);
				}
				enviados = aux.getBytes();
				
			

			//Se envía el datagrama al cliente
			DatagramPacket paqEnviado = new DatagramPacket (enviados, enviados.length, IPOrigen, puerto); 
			serverSocket.send(paqEnviado);
		}
	}catch(IOException e) {
		e.printStackTrace();
	}
	//serverSocket.close(); 
}//Fin de main

}
