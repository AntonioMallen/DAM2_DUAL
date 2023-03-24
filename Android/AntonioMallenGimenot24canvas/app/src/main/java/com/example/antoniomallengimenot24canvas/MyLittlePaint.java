package com.example.antoniomallengimenot24canvas;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MyLittlePaint extends AppCompatActivity {

    private ViewGroup paint;
    private MyCustomView myCanvasView;
    private EditText tamano;
    private String[] colores={"Negro","Azul","Rojo","Verde","Blanco"};
    private String colorElegido=colores[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paint=(ViewGroup)findViewById(R.id.pintar);
        tamano=(EditText) findViewById(R.id.size);
        tamano.setText("12");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                colorElegido=colores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                colorElegido=colores[0];
            }

        });


        myCanvasView = new MyCustomView(this);
        myCanvasView.setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN);

        paint.addView(myCanvasView);
    }


    public void set(View view){
        myCanvasView.set(Float.parseFloat(tamano.getText().toString()),colorElegido);
    }

    public void reset(View view){
        myCanvasView.reset();
    }

}