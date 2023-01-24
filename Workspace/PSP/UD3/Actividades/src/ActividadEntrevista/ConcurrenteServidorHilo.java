package ActividadEntrevista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConcurrenteServidorHilo extends Thread {

	private Socket client;
	private int numero;

	public ConcurrenteServidorHilo(Socket client, int numero) {
		this.client = client;
		this.numero=numero;
	}

	public void run(){

		PrintWriter fsalida=null;
		BufferedReader fentrada=null;
		String[] preguntas = {"¿Cual es tu color favorito?","¿Prefieres gatos o perros?","¿Que deporte te gusta?","¿?"};
		String respuesta="";
		try {
			fsalida= new PrintWriter(client.getOutputStream(),true);
			fentrada= new BufferedReader(new InputStreamReader(client.getInputStream()));
			for(int i =1; i<=4;i++) {


				fsalida.println(preguntas[i]);

				respuesta=fentrada.readLine();

				System.out.println("Cliente numero "+numero+" pregunta "+i);

			}

			System.out.println("Cerrando conexion... ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fsalida.close();
			try {
				fentrada.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
