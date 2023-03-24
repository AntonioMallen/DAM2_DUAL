package com.example.imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private ImageView langosta,llamar,colgar;
    private int contador=0;
    private int rando=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (TextView)findViewById(R.id.tv1);
        langosta = (ImageView) findViewById(R.id.imageView2);
        colgar =(ImageView) findViewById(R.id.imageView);
        llamar =(ImageView) findViewById(R.id.boton1);
        langosta.setVisibility(View.INVISIBLE);
        colgar.setVisibility(View.INVISIBLE);
        Random random = new Random();
        rando=random.nextInt(11);
    }
    public void llamar(View view){
        tv1.setText("Has llamado");
        contador++;
        if(contador==rando) {
            langosta.setVisibility(View.VISIBLE);
            tv1.setText("Tu te lo has buscado");
        }
        llamar.setVisibility(View.INVISIBLE);
        colgar.setVisibility(View.VISIBLE);
    }
    public void colgar(View view){
        tv1.setText("Has colgado");
        langosta.setVisibility(View.INVISIBLE);
        llamar.setVisibility(View.VISIBLE);
        colgar.setVisibility(View.INVISIBLE);
    }

}