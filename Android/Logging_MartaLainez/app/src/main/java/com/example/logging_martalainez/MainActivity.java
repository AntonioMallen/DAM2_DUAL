package com.example.logging_martalainez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tv1, tv2;
    private Intent intent;
    private int contador = 0;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        // instancio intent
        intent = new Intent(MainActivity.this,Actividad2.class);
        // instancio spinner
        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.usuarios,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void onClick(View view){
        String usuario;
        boolean correcto = false;
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        contador++;

        if(tv2.getText().toString().equals("123")){
            correcto = true;
        }
        else{
            String textoToast = "Incorrecto. LLevas " + contador + " /3 intentos.";
            Toast notification = Toast.makeText(getApplicationContext(), textoToast , Toast.LENGTH_LONG);
            notification.setGravity(Gravity.TOP|Gravity.LEFT,250,0);
            notification.show();
        }
        if(contador == 3 || correcto){
            if(correcto) {
                String[] datos = tv1.getText().toString().split("@");
                usuario = datos[0];
                intent.putExtra("usuario", usuario);
                intent.putExtra("intentos", contador+"");
            }
            intent.putExtra("booleano", correcto);
            startActivity(intent);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
        intent.putExtra("id", text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(),"Sin tipo de usuario seleccionado",Toast.LENGTH_SHORT).show();
    }
}