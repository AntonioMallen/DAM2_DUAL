package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre,datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=(EditText) findViewById(R.id.nombre);
        datos=(EditText) findViewById(R.id.datos);

    }
    public void grabar(View view){

        SharedPreferences preferencias=getSharedPreferences("agenda",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        String name=nombre.getText().toString();
        String dato=datos.getText().toString();
        editor.putString( name ,dato);
        editor.commit();
    }

    public void recuperar(View view){
        SharedPreferences prefe=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String nombre_campo=nombre.getText().toString();
        String texto=prefe.getString(nombre_campo,"");
            if (texto.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "No hay datos de ese nombre", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                datos.setText(texto);
            }
    }
}