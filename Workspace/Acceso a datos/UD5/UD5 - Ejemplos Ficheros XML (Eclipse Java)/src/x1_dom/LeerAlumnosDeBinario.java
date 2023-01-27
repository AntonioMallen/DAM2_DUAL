package x1_dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerAlumnosDeBinario {
  
	// Lee todos los alumnos del fichero binario 'alumnos_aleat_datos.dat',
	// escribe en consola estos alumnos y
	// escribe en consola el número de alumnos leídos del fichero binario.
  	public static void main(String[] args) {
		RandomAccessFile flujoEntrada = null;
		try {
			File fichero = new File("data/alumnos_aleat_datos.dat");
			flujoEntrada = new RandomAccessFile(fichero, "r");
			flujoEntrada.seek(0);			
			int contadorAlumnos = 0;
			while (flujoEntrada.getFilePointer() < flujoEntrada.length()) {
				int codigo = flujoEntrada.readInt();
				char[] vectorCaracteres = new char[Alumno.LONGITUD_NOMBRE];
				for (int i = 0 ; i < vectorCaracteres.length ; i++) {         
					vectorCaracteres[i] = flujoEntrada.readChar(); 
				}
				String nombre = new String(vectorCaracteres); 
				double nota = flujoEntrada.readDouble();
				if (codigo > 0) {
					Alumno alumno = new Alumno(codigo, nombre, nota);
					System.out.println(alumno.toString());
					contadorAlumnos++;
				}
			}
			System.out.println("Se han leído " + contadorAlumnos + 
                               " alumnos del fichero binario.");
		} 
		catch (FileNotFoundException fnfe) {
			System.out.println("Error al abrir el fichero:");
			System.out.println(fnfe.getMessage());
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("Error al leer del fichero:");
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
		finally {
			try {
				if (flujoEntrada != null) {
					flujoEntrada.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero:");
				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
			}
		}
	}
  
}
