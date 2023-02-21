
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    

	public static void main(String[] args) {
		int puerto = 60000;// Puerto 
		ServerSocket servidor=null;
		int contador=0;
		ArrayList<Socket> lista =new ArrayList<Socket>();
		try {
			servidor = new ServerSocket(puerto);
		
			while(true) {
				contador++;
				Socket clienteConectado = servidor.accept();
                                lista.add(clienteConectado);
				System.out.println("Clientes: "+contador);
                                
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

