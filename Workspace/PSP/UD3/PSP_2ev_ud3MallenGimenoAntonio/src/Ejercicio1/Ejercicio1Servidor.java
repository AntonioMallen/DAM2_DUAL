package Ejercicio1;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import entrada.Teclado;


public class Ejercicio1Servidor {

	public static void main(String[] args) {
		int puerto = 60000;// Puerto 
		ServerSocket servidor=null;
		ArrayList<Equipo> Equipos = new ArrayList<Equipo>();
		try {
			servidor = new ServerSocket(puerto);
			
			int cantEquipos=Teclado.leerEntero("¿Cantidad de equipos?");
			for(int contador =1; contador<=cantEquipos;contador++) {
				String nombre= Teclado.leerCadena("¿Nombre del equipo "+contador+"?");
				Equipo equipo= new Equipo(nombre);
				Equipos.add(equipo);
			}
			
			
			while(true) {
				Socket clienteConectado = servidor.accept(); 
				Ejercicio1Hilo hiloCliente = new Ejercicio1Hilo(clienteConectado,Equipos);
				hiloCliente.start();
			}
			
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
