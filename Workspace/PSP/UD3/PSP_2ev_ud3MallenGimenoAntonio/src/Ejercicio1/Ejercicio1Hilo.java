package Ejercicio1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ejercicio1Hilo extends Thread {

	private Socket client;
	ArrayList<Equipo> Equipos = new ArrayList<Equipo>();


	public Ejercicio1Hilo(Socket client,ArrayList<Equipo> Equipos) {
		this.client = client;
		this.Equipos=Equipos;
	}

	public void run(){

		PrintWriter fsalida=null;
		BufferedReader fentrada=null;
		try {
			fsalida= new PrintWriter(client.getOutputStream(),true);
			fentrada= new BufferedReader(new InputStreamReader(client.getInputStream()));


			String AuxEquipos="([ ";
			for(Equipo equipo: Equipos) {
				AuxEquipos+=equipo.getEquipo()+", ";
			}
			AuxEquipos+="])";
			fsalida.println("Indica nombre de equipo "+AuxEquipos);

			String respuesta=fentrada.readLine();
			for(Equipo equipo: Equipos) {
				if(equipo.getNombre().equals(respuesta)) {
					equipo.sumarVoto();
				}
			}

			String salida="";
			for(Equipo equipo: Equipos) {
				salida+=equipo;
			}
			fsalida.print(salida);

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
