package com.example.antoniomallenep1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Metodo creado para sustituir el layout por el fragment
     *
     * @param view: Parametro puesto para llamar al onclick en el boton
     */
    public void displayFragment(View view){
        Formulario simpleFragment = Formulario.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout,simpleFragment).addToBackStack(null).commit();
    }
}