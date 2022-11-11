package com.example.ap65_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setTitle("AntonioMallen");
        actionBar.setSubtitle("Soy un subtítulo del Action Bar");
        actionBar.setIcon(R.drawable.images);
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF0000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        setContentView(R.layout.activity_main);
        texto = (TextView) findViewById(R.id.miTexto);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.llamar:
                Toast toast=Toast.makeText(getApplicationContext(), getString(R.string.llamar),Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.cam:
                toast=Toast.makeText(getApplicationContext(), getString(R.string.cam),Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.MnuOpc1:
                texto.setText(getString(R.string.opcion1));
                return true;
            case R.id.MnuOpc2:
                texto.setText(getString(R.string.opcion2));
                return true;
            case R.id.MnuOpc3:
                texto.setText(getString(R.string.opcion3));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mostrar(View view){
        actionBar.show();
    }
    public void ocultar(View view){
        actionBar.hide();
    }
}
