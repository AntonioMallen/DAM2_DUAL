package com.example.ejemplomarta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class SecondActivity extends AppCompatActivity {
    private TextView texto;
    private EditText url;
    private WebView web1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        texto = (TextView) findViewById(R.id.text);
        url = (EditText) findViewById(R.id.enlace);
        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        texto.setText("Bienvenido, " + username);


    }

    public void buscar (View view){
        String dato=url.getText().toString();
        web1=(WebView)findViewById(R.id.webView);
        web1.setWebViewClient(new WebViewClient());
        WebSettings webSettings = web1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web1.loadUrl("http://" + dato);
    }

}