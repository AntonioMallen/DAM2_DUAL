package com.example.logging_martalainez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad3 extends AppCompatActivity {
    WebView wv1;
    TextView tv6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        Bundle contenidos = getIntent().getExtras();
        tv6 = (TextView)findViewById(R.id.tv6);
        String url = contenidos.getString("url");
        tv6.setText(url);
        //recoger objeto WebView de la vista
        //recoger el dato enviado por el intent
        wv1 = (WebView)findViewById(R.id.wv1);
        wv1.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv1.loadUrl("http://" + url);
    }
    public void onClick(View view){
        Intent intent= new Intent(Actividad3.this,Actividad2.class);
        startActivity(intent);
    }
}
