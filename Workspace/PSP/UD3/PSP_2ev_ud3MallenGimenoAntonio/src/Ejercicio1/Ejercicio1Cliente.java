package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import entrada.Teclado;

public class Ejercicio1Cliente {

	public static void main(String[] args) {
		Socket cliente=null;
		String host = "localhost"; 
		int puerto = 60000;//puerto remoto 

		// ABRIR SOCKET 
		try {
			cliente = new Socket(host, puerto);
			PrintWriter flujoSalida= new PrintWriter(cliente.getOutputStream(),true);
			BufferedReader flujoEntrada= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			
			
			String pregunta=flujoEntrada.readLine();
			System.out.println(pregunta); 

			String respuesta=Teclado.leerCadena("");
			flujoSalida.println(respuesta);
			

			System.out.println("Gracias por tu voto");
			System.out.println(" -- Recuento -- ");
			/*
			 * pongo este caracter ya que es uno especial y asi puedo enviar varias lineas
			 */
			String[] salida=flujoEntrada.readLine().split("☺"); 
			for(String mostrar: salida) {
				System.out.println(mostrar);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
