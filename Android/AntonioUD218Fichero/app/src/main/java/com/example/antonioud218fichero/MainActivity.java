package com.example.antonioud218fichero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> fechas = new ArrayList<String>();
    ArrayList<String> informaciones = new ArrayList<String>();

    private EditText editTextDate,editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDate=(EditText) findViewById(R.id.editTextDate);
        editText=(EditText) findViewById(R.id.editText);
        try {

            String[] archivos = fileList();
            if (existe(archivos, "notas.txt")) {
                InputStreamReader archivo =
                        new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while(linea!=null){
                    String [] datosTemp = linea.split(":");
                    fechas.add(datosTemp[0]);
                    informaciones.add(datosTemp[1]);
                    linea = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void guardar (View view){
            OutputStreamWriter archivo =null;
            try {
                archivo = new OutputStreamWriter(openFileOutput(
                        "notas.txt", Activity.MODE_PRIVATE));
                String dato = editTextDate.getText() + ":"+editText.getText();
                String [] datosTemp = dato.split(":");
                fechas.add(datosTemp[0]);
                informaciones.add(datosTemp[1]);
                archivo.write(dato);
                archivo.flush();
                archivo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cargar(View view){
            int numAlmacenado =0;
            String fechaCargar = String.valueOf(editTextDate.getText());
            if (fechaCargar!=null) {
                for(String fecha: fechas){
                    if(fecha.equals(fechaCargar)){
                        numAlmacenado=fechas.indexOf(fecha);
                    }
                }
                editText.setText(informaciones.get(numAlmacenado));
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