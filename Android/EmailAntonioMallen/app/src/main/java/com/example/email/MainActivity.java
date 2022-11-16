package com.example.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private ImageButton boton;
    private TextView texto;
    private int contador=0;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        boton=(ImageButton) findViewById(R.id.boton);
        texto=(TextView) findViewById(R.id.mostrar);
        texto.setText("Numero de intentos: "+contador);
        intent=new Intent(MainActivity.this, AciertoFallo.class);
    }

    public void verificar(View v) {
        if(contador==3){
            intent.putExtra("Acierto",false);
            startActivity(intent);
        }
        if(validarEmail(email.getText().toString())) {
            String passwd = password.getText().toString();
            String email1[] = email.getText().toString().split("[@]");
            String username = email1[0];
            if (passwd.equalsIgnoreCase("hola1234")) {
                intent.putExtra("username",username);
                intent.putExtra("intentos",contador);
                intent.putExtra("Acierto",true);
                startActivity(intent);
            }else{
                contador++;
                texto.setText("Numero de intentos: "+contador);
            }
        }else {
            Toast.makeText(this, "El email no es correcto", Toast.LENGTH_LONG).show();
            contador++;
            texto.setText("Numero de intentos: " + contador);
        }
    }

    public static Boolean validarEmail(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        return email.matches(regex);
    }
}