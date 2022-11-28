package com.example.email;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AciertoFallo extends AppCompatActivity {
    private TextView texto;
    private Button volver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        volver=(Button) findViewById(R.id.button);
        texto=(TextView) findViewById(R.id.resultado);

        Intent intent = getIntent();
        Boolean acertado = intent.getBooleanExtra("Acierto",false);
        if (acertado){
            String username = intent.getStringExtra("username");
            int intentos = intent.getIntExtra("intentos",0);
            texto.setText("Hola, "+username+" has necesitado "+ intentos +" intentos.");
        }else {
            texto.setText("Demasiados intentos fallidos");
        }
    }

    public void volver(View view){
        Intent intent=new Intent(AciertoFallo.this, MainActivity.class);
        startActivity(intent);
    }
}
