package com.example.antoniomallengimenocalculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText num1,num2;
    private TextView resultado;
    private CheckBox mostrar;
    private Button cuadrado,raiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrar = (CheckBox) findViewById(R.id.mostrar);
        num1 = (EditText) findViewById(R.id.numero1);
        num2 = (EditText) findViewById(R.id.numero2);
        resultado = (TextView) findViewById(R.id.resultado);
        cuadrado = (Button) findViewById(R.id.cuadrado);
        raiz = (Button) findViewById(R.id.raiz);

        /*
        Para ocultar los campos nada mas carga la aplicacion
         */
        cuadrado.setVisibility(View.INVISIBLE);
        raiz.setVisibility(View.INVISIBLE);
    }
    /*
    Con este metodo comprobaremos cuando la checkbox es usada,
    y mostraremos dependiendo de si esta marcada o no
     */
    public void mostrarBotones(View v) {
        CheckBox checkBox = (CheckBox)v;
        if(mostrar.isChecked()){
            cuadrado.setVisibility(View.VISIBLE);
            raiz.setVisibility(View.VISIBLE);
        }else{
            cuadrado.setVisibility(View.INVISIBLE);
            raiz.setVisibility(View.INVISIBLE);
        }
    }
    /*
    Con el metodo borrar ponemos el texto de los dos numeros introducidos a
    una cadena vacia para que no salga nada y el resultado lo dejamos como cuando carga
     */
    public void borrar(View view){
        num1.setText("");
        num2.setText("");
        resultado.setText("0");
    }
    /*
    El metodo suma trae los dos numeros introducidos por teclado,
    los castea de String a double(por si tienen decimales), los suma y
    los muestra por pantalla a traves del setText
     */
    public void sumar (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        double suma=nro1+nro2;
        String res=String.valueOf(suma);
        resultado.setText(res);
    }
    /*
    En el metodo restar traemos los numeros y los casteamos a double
    (por si tienen decimales), lo resta, y lo pasamos con un setText para
    mostrarlo por pantalla
     */
    public void restar (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        double resta=nro1-nro2;
        String res=String.valueOf(resta);
        resultado.setText(res);
    }
    /*
    Para el metodo de multiplicar simplemente llamamos a los numeros,
    les hacemos un cast a double(por si tienen decimales) y hacemos
    un set text del resultado para mostrarlo por pantalla
     */
    public void multiplicar (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        double mult=nro1*nro2;
        String res=String.valueOf(mult);
        resultado.setText(res);
    }
    /*
    Creamos un metodo para la division, en este comprobaremos que
    el numero 2 no sea menor que 0 ya que seria un numero no real
     */
    public void dividir (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        if(nro2!=0) {
            double div = nro1 / nro2;
            String res = String.valueOf(div);
            resultado.setText(res);
        }else{
            resultado.setText("Syntax Error");
        }
    }
    /*
    creamos una funcion cuadrado que recibe dos numeros
    y realiza la operacion de cuadrado a traves
    del metodo Math, este es mostrado a traves del
    setText.
     */
    public void cuadrado (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        double cuad=Math.pow(nro1,nro2);
        String res=String.valueOf(cuad);
        resultado.setText(res);
    }
    /*
    Este metodo recibe dos numeros y hace la raiz del primero con base
    del segundo numero, esto lo realiza elevando por 1/ del segundo numero
    Despues lo muestra por pantalla a traves del setText
     */
    public void raiz (View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        double nro1=Integer.parseInt(valor1);
        double nro2=Integer.parseInt(valor2);
        double raiz=Math.pow(nro1, 1.0/nro2);
        String res=String.valueOf(raiz);
        resultado.setText(res);
    }


}