package com.example.aplicaciongirarmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.OrientationEventListener;

public class MainActivity extends AppCompatActivity implements OrientationEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onOrientationChanged(int i) {

    }
}