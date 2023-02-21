
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JTextArea;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alu
 */
public class HiloLeer extends Thread{
    private BufferedReader fEntrada;
    private JTextArea etiqueta;

    public HiloLeer(BufferedReader fEntrada, JTextArea etiqueta) {
        this.fEntrada = fEntrada;
        this.etiqueta = etiqueta;
    }
    
    public void run(){
        try{
            String respuesta="";
            respuesta= fEntrada.readLine();
            while(respuesta!=null && !respuesta.equals("Fin")){
                etiqueta.setText(etiqueta.getText()+respuesta+"\n");
                respuesta=fEntrada.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
