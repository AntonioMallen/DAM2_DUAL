
package com.example.antoniomallengimenot22;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antoniomallengimenot22.R;

public class MainActivity extends AppCompatActivity {

    private EditText nombre,contra;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=(EditText) findViewById(R.id.nombre);
        contra=(EditText) findViewById(R.id.contrasena);

    }
    public void grabar(View view){

        SharedPreferences preferencias=getSharedPreferences("agenda",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        String name=nombre.getText().toString();
        String texto1=preferencias.getString(name,"");
        if (texto1.equals("")) {
            String dato = contra.getText().toString();
            editor.putString(name, dato);
            editor.commit();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Esa cuenta ya existe", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void recuperar(View view){
        SharedPreferences prefe=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String nombre_campo=nombre.getText().toString();
        String texto=prefe.getString(nombre_campo,"");
        if (texto.equals(contra.getText().toString())) {
            intent=new Intent(MainActivity.this, SecondActivity.class);
            String name = nombre.getText().toString();
            intent.putExtra("username",name);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Nombre o contraseña incorrecta", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}