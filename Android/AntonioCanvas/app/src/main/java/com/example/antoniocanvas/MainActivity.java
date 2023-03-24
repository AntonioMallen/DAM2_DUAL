package com.example.antoniocanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
/*

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actividad1(View view){
        Intent intent = new Intent(this, Actividad1.class);
        startActivity(intent);
    }

    public void actividad2(View view){
        Intent intent = new Intent(this, Actividad2.class);
        startActivity(intent);
    }

    public void actividad3(View view){
        Intent intent = new Intent(this, Actividad3.class);
        startActivity(intent);
    }

}