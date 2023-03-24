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

    private EditText editTextDate,editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDate=(EditText) findViewById(R.id.editTextDate);
        editText=(EditText) findViewById(R.id.editText);

    }

    public void guardar (View view){
        OutputStreamWriter archivo =null;
        String fecha=editTextDate.getText().toString().replace("/","-");
        try {
            archivo = new OutputStreamWriter(openFileOutput(
                    fecha+".txt", Activity.MODE_PRIVATE));
            String dato = ""+editText.getText();
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
        String dato="";
        String fecha=editTextDate.getText().toString().replace("/","-");
        try {
            String[] archivos = fileList();
            if (existe(archivos, fecha)) {
                InputStreamReader archivo =
                        new InputStreamReader(openFileInput(fecha+".txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while(linea!=null){
                    dato+=linea;
                    linea = br.readLine();
                }
                editText.setText(dato);
            }
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