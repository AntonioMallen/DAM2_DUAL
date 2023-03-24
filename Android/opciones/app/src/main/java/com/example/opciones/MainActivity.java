package com.example.opciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText num1,num2;
    private TextView resultado;
    private RadioButton sumar,restar;
    private RadioButton multiplicar,dividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumar=(RadioButton)findViewById(R.id.sumar);
        restar=(RadioButton)findViewById(R.id.restar);
        multiplicar=(RadioButton)findViewById(R.id.multiplicar);
        dividir=(RadioButton)findViewById(R.id.dividir);

        num1=(EditText)findViewById(R.id.numero1);
        num2=(EditText)findViewById(R.id.numero2);
        resultado=(TextView)findViewById(R.id.resultado);
    }

    public void operar(View view) {
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);

        if(sumar.isChecked()){

            int suma=nro1+nro2;
            String res=String.valueOf(suma);
            resultado.setText(res);

        }else if(restar.isChecked()){

            int rest=nro1-nro2;
            String res=String.valueOf(rest);
            resultado.setText(res);

        }else if(multiplicar.isChecked()){

            int mult=nro1*nro2;
            String res=String.valueOf(mult);
            resultado.setText(res);

        }else if (dividir.isChecked()){
            if(nro2!=0) {
                int div = nro1 / nro2;
                String res = String.valueOf(div);
                resultado.setText(res);
            }else{
                resultado.setText("Error");
            }
        }else{
            resultado.setText("Error");
        }

    }
}