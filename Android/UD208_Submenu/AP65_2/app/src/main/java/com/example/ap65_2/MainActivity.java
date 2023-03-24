package com.example.ap65_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView texto;

    public static final int MNU_OPC1 = 1;
    private static final int MNU_OPC2 = 2;
    public static final int MNU_OPC1A = 3;
    private static final int MNU_OPC1B = 4;
    public static final int MNU_OPC1C = 5;
    private static final int MNU_OPC1D = 6;

    private static final int MNU_OPC2A = 7;
    public static final int MNU_OPC2B = 8;
    private static final int MNU_OPC2C = 9;
    private static final int MNU_OPC2D = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto=(TextView) findViewById(R.id.miTexto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu smnu1 = menu.addSubMenu(Menu.NONE, MNU_OPC1, Menu.NONE, "DIAS DE LA SEMANA");
        smnu1.add(Menu.NONE, MNU_OPC1A, Menu.NONE, "LUNES");
        smnu1.add(Menu.NONE, MNU_OPC1B, Menu.NONE, "MARTES");
        smnu1.add(Menu.NONE, MNU_OPC1C, Menu.NONE, "MIÉRCOLES");
        smnu1.add(Menu.NONE, MNU_OPC1D, Menu.NONE, "JUEVES");


        SubMenu smnu2 = menu.addSubMenu(Menu.NONE, MNU_OPC2, Menu.NONE, "MESES DE AÑO");
        smnu2.add(Menu.NONE, MNU_OPC2A, Menu.NONE, "ENERO");
        smnu2.add(Menu.NONE, MNU_OPC2B, Menu.NONE, "FEBRERO");
        smnu2.add(Menu.NONE, MNU_OPC2C, Menu.NONE, "MARZO");
        smnu2.add(Menu.NONE, MNU_OPC2D, Menu.NONE, "ABRIL");


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mensaje = "";
        switch (item.getItemId()) {
            case 3:
                texto.setText("PULSADO LUNES");
                return true;
            case 4:
                texto.setText("PULSADO MARTES");
                return true;
            case 5:
                texto.setText("PULSADO MIÉRCOLES");
                return true;
            case 6:
                texto.setText("PULSADO JUEVES");
                return true;
            case 7:
                texto.setText("PULSADO ENERO");
                return true;
            case 8:
                texto.setText("PULSADO FEBRERO");
                return true;
            case 9:
                texto.setText("PULSADO MARZO");
                return true;
            case 10:
                texto.setText("PULSADO ABRIL");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
