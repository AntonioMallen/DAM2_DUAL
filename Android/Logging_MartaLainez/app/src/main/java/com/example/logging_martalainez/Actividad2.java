package com.example.logging_martalainez;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad2 extends AppCompatActivity {
    private TextView tv3, tv4,tv5,tv7,tv8;
    private Intent intentAct3,intentMain;
    private String url;
    private RadioButton rb1,rb2;
    private CheckBox cb1,cb2,cb3;
    private ImageView iv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv8 = (TextView)findViewById(R.id.tv8);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb3 = (CheckBox)findViewById(R.id.cb3);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);

        Bundle contenidos = getIntent().getExtras();
        intentAct3 = new Intent(Actividad2.this,Actividad3.class);
        intentMain = new Intent(Actividad2.this,MainActivity.class);
        boolean correcto = contenidos.getBoolean("booleano");
        String id = contenidos.getString("id");
        if(correcto){
            tv3.setText("Hola " + id + " "+ contenidos.getString("usuario") + ",");
            tv4.setText("Has necesitado: " + contenidos.getString("intentos") + " intentos.");
        }
        else{
            tv4.setText(id + ", has llegado al límite de intentos");
        }
        Toast notification = Toast.makeText(getApplicationContext(), "Introduzca un URL" , Toast.LENGTH_LONG);
        notification.setGravity(Gravity.TOP|Gravity.LEFT,250,0);
        notification.show();
    }
    public void onClickUrl(View view){
        url = tv5.getText().toString();
        intentAct3.putExtra("url", url);
        startActivity(intentAct3);
    }
    public void onClickSalir(View view){
        startActivity(intentMain);
    }
    public void validarCheckBox(View view){
        String cadena = "Checkboxes seleccionados:";
        if(cb1.isChecked()){
            cadena += "\nOpción 1";
            //Toast.makeText(getApplicationContext(),"checkbox 1", Toast.LENGTH_SHORT).show();
        }
        if(cb2.isChecked()){
            cadena += "\nOpción 2";
            //Toast.makeText(getApplicationContext(),"checkbox 2", Toast.LENGTH_SHORT).show();
        }
        if(cb3.isChecked()){
            cadena += "\nOpción 3";
            //Toast.makeText(getApplicationContext(),"checkbox 3", Toast.LENGTH_SHORT).show();
        }
        tv7.setText(cadena);
    }
    public void validarRadio(View view){
        String cadena = "Radiobutton seleccionado:";
        if(rb1.isChecked()){
            cadena += "\nRadio 1";
            Toast.makeText(getApplicationContext(),"Radio 1", Toast.LENGTH_SHORT).show();
            iv.setVisibility(View.VISIBLE); // Crashea ;-;
        }
        if(rb2.isChecked()){
            cadena += "\nRadio 2";
            Toast.makeText(getApplicationContext(),"Radio 2", Toast.LENGTH_SHORT).show();
            iv.setVisibility(View.INVISIBLE); // Crashea ;-;
        }
        tv8.setText(cadena);
    }

}
