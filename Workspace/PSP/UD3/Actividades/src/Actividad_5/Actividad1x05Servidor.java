package Actividad_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Actividad1x05Servidor {

	public static void main(String[] args) {
		int puerto = 60000;// Puerto 
		ServerSocket servidor=null;
		
		try {
			servidor = new ServerSocket(puerto);
			
			Socket clienteConectado = null; 
			clienteConectado = servidor.accept(); 
			
			InputStream entrada = null; 
			entrada = clienteConectado.getInputStream(); 
			DataInputStream flujoEntrada = new DataInputStream(entrada); 
			String cadena=flujoEntrada.readUTF(); 

			switch(cadena) {
				case("¿como te llamas?"):
				
					break;
				case(""):
					
					break;
				default:
					System.out.println("Pregunta no contemplada");
			}
		
			OutputStream salida = null; 
			salida = clienteConectado.getOutputStream(); 
			DataOutputStream flujoSalida = new DataOutputStream(salida); 
			flujoSalida.writeUTF("");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				servidor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
