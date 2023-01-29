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
	ArrayList<ArrayList> respuestasTotales = new ArrayList<ArrayList>();
	ArrayList<String> respuestas = new ArrayList<String>();

	public ConcurrenteServidorHilo(Socket client, int numero,ArrayList<ArrayList> respuestas) {
		this.client = client;
		this.numero=numero;
		this.respuestasTotales=respuestas;
	}

	public void run(){

		PrintWriter fsalida=null;
		BufferedReader fentrada=null;
		String[] preguntas = {"¿Cual es tu color favorito?","¿Prefieres gatos o perros?","¿Que deporte te gusta?","¿Como te llamas?"};
		String respuesta="";
		try {
			fsalida= new PrintWriter(client.getOutputStream(),true);
			fentrada= new BufferedReader(new InputStreamReader(client.getInputStream()));
			for(int i =1; i<=4;i++) {


				fsalida.println(preguntas[i-1]);

				respuesta=fentrada.readLine();
				respuestas.add(respuesta);

				//System.out.println("Cliente numero "+numero+" pregunta "+i);

			}
			respuestasTotales.add(respuestas);
			System.out.println(respuestasTotales);
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
	
	
	private void imprimir() {
		for(ArrayList<String> resp: respuestasTotales) {
			for(String cadena: resp) {
				
			}
		}
	}

}
