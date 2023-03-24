
import java.io.PrintWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alu
 */
public class HiloEscribir extends Thread{
    private PrintWriter fSalida;
    private String mensaje;
    
    public HiloEscribir(PrintWriter fSalida, String nombre, String mensaje){
        this.fSalida=fSalida;
        this.mensaje=nombre+": "+mensaje;
    }

    public HiloEscribir(PrintWriter fSalida, String mensaje) {
        this.fSalida = fSalida;
        this.mensaje = mensaje;
    }
    
    public void run(){
        fSalida.println(mensaje);
        fSalida.flush();
    }
    
}   

