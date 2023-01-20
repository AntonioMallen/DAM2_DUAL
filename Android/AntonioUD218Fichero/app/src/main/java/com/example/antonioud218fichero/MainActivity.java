package com.example.antonioud218fichero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private String dato;
    private OutputStreamWriter archivo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] archivos = fileList();
        if (existe(archivos, "notas.txt")) {


        }
    }

        public void guardar (View view){
            try {
                archivo = new OutputStreamWriter(openFileOutput(
                        "notas.txt", Activity.MODE_PRIVATE));
                archivo.write(dato);
                archivo.flush();
                archivo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean existe (String[] archivos, String comprobar){
            for(String string: archivos){
                if(string.equals(comprobar)) {
                    return true;
                }
            }
            return false;
        }



}