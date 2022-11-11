package ud1x05;

import java.io.File;
import java.io.IOException;

public class ProcesaValorRetorno {

	public static void main(String[] args) {

		Runtime runtime = Runtime.getRuntime();
		try{

			Process hijo = runtime.exec("java ud1x05/ValorRetorno", null ,  new File(".\\bin"));
			int respuesta;
			try {				
				respuesta = hijo.waitFor();

				if(respuesta==0) {
					System.err.println("Proceso finalizado correctamente");
				}else if(respuesta==-1) {
					System.err.println("Error en proceso de numeración");
					throw new IOException();
				}else if (respuesta==1) {
					System.out.println("El Proceso ha finalizado con : " + respuesta+ " como valor de retorno");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				hijo.destroy();
			}
		}
		catch (IOException ex){
			System.err.println("Error de Entrada/Salida" + ex.toString());
			System.exit(-1);
		}



	}
}