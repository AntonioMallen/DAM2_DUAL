package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    //public static final String message22 = "message2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void suma(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        EditText editText2 = (EditText) findViewById(R.id.editTextNumber2);

        String message = editText.getText().toString();
        String message2 = editText2.getText().toString();

        int num1=Integer.parseInt(message);
        int num2=Integer.parseInt(message2);

        String resultado = (String.valueOf(num1+num2));

        TextView textView = findViewById(R.id.textView2);
        textView.setText(resultado);

        //intent.putExtra(EXTRA_MESSAGE, resultado);
        //startActivity(intent);
    }
    public void restar(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        EditText editText2 = (EditText) findViewById(R.id.editTextNumber2);

        String message = editText.getText().toString();
        String message2 = editText2.getText().toString();

        int num1=Integer.parseInt(message);
        int num2=Integer.parseInt(message2);

        String resultado = (String.valueOf(num1-num2));

        TextView textView = findViewById(R.id.textView2);
        textView.setText(resultado);

        //intent.putExtra(EXTRA_MESSAGE, resultado);
        //startActivity(intent);
    }
    public void multiplicar(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        EditText editText2 = (EditText) findViewById(R.id.editTextNumber2);

        String message = editText.getText().toString();
        String message2 = editText2.getText().toString();

        int num1=Integer.parseInt(message);
        int num2=Integer.parseInt(message2);

        String resultado = (String.valueOf(num1*num2));

        TextView textView = findViewById(R.id.textView2);
        textView.setText(resultado);

        //intent.putExtra(EXTRA_MESSAGE, resultado);
        //startActivity(intent);
    }
    public void dividir(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        EditText editText2 = (EditText) findViewById(R.id.editTextNumber2);

        String message = editText.getText().toString();
        String message2 = editText2.getText().toString();

        int num1=Integer.parseInt(message);
        int num2=Integer.parseInt(message2);

        String resultado = (String.valueOf(num1/num2));

        TextView textView = findViewById(R.id.textView2);
        textView.setText(resultado);

        //intent.putExtra(EXTRA_MESSAGE, resultado);
        //startActivity(intent);
    }

}