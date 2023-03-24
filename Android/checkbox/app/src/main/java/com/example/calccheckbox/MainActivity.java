package com.example.calccheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox sumar,restar,multiplicar,dividir;
    private EditText num1,num2;
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumar=(CheckBox)findViewById(R.id.checkBox);
        restar=(CheckBox)findViewById(R.id.checkBox2);
        multiplicar=(CheckBox)findViewById(R.id.checkBox4);
        dividir=(CheckBox)findViewById(R.id.checkBox3);

        num1=(EditText)findViewById(R.id.numero1);
        num2=(EditText)findViewById(R.id.numero2);
        resultado=(TextView)findViewById(R.id.resultado);
    }
    public void operar(View view) {
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        String texto="";
        if(sumar.isChecked()){
            int suma=nro1+nro2;
            String res=String.valueOf(suma);
            texto+="La suma es: "+res+"\n";
        }
        if(restar.isChecked()){
            int rest=nro1-nro2;
            String res=String.valueOf(rest);
            texto+="La resta es: "+res+"\n";
        }
        if(multiplicar.isChecked()){
            int mult=nro1*nro2;
            String res=String.valueOf(mult);
            texto+="La multiplicacion es: "+res+"\n";
        }
        if (dividir.isChecked()){
            if(nro2!=0) {
                int div = nro1 / nro2;
                String res = String.valueOf(div);
                texto+="La division es: "+res+"\n";
            }else{
                texto+=("No puedes dividir entre 0");
            }
        }
        if(!sumar.isChecked()&&!restar.isChecked()&&!multiplicar.isChecked()&&!dividir.isChecked()){
            texto=("Debes seleccionar una casilla");
        }
        resultado.setText(texto);
    }
}
