package com.example.ampractica22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void boton1 (View view){
        setContentView(R.layout.linearlayout);
    }
    public void boton2 (View view){
        setContentView(R.layout.linearlayout2);
    }
    public void boton3 (View view){
        setContentView(R.layout.linearlayout3);
    }
    public void boton4 (View view){
        setContentView(R.layout.linearlayout4);
    }
    public void boton5 (View view){
        setContentView(R.layout.framelayout);
    }
    public void boton6 (View view){
        setContentView(R.layout.relativelayout);
    }
    public void boton7 (View view){
        setContentView(R.layout.linearlayout7);
    }
    public void boton8 (View view){
        setContentView(R.layout.tablelayout);
    }

}