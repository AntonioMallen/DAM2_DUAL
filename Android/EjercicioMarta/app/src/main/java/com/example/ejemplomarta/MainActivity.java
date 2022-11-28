package com.example.ejemplomarta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    /*
    Haz una app que te pida el nombre de usuario,
    en la siguiente pantalla escribe bienvenido nombre de usuario y pidele un enlace,
    en la siguiente pantalla enseña ese enlace
     */
    private EditText name;
    private ImageButton boton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.name);

        boton=(ImageButton) findViewById(R.id.boton);

        intent=new Intent(MainActivity.this, SecondActivity.class);
    }

    public void verificar(View v) {
        String nombre = name.getText().toString();
        intent.putExtra("username",nombre);
        startActivity(intent);
    }

}