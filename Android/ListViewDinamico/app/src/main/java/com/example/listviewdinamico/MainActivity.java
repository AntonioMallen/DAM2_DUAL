package com.example.listviewdinamico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> telefonos;
    private ArrayAdapter adaptador;
    private EditText nombre,telefono;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre= (EditText) findViewById(R.id.name);
        telefono= (EditText) findViewById(R.id.phone);
        lista= (ListView) findViewById(R.id.lista);


        SharedPreferences prefe=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        Set<String> set = prefe.getStringSet("key", null);
        set.add("Test : 123");
        telefonos=new ArrayList<String>(set);


        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefonos);

        lista.setAdapter(adaptador);


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Eliminar");
                builder.setMessage("¿Quieres eliminar este contacto?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        telefonos.remove(i);
                        adaptador.notifyDataSetChanged();
                        SharedPreferences preferencias=getSharedPreferences("agenda", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferencias.edit();
                        Set<String> set = new HashSet<String>();
                        set.addAll(telefonos);
                        editor.putStringSet("key", set);
                        editor.commit();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return false;
            }
        });

    }


    public void anadir(View view){
        String nom=nombre.getText().toString();
        String tel=telefono.getText().toString();

        String resul = nom +" : "+ tel;
        telefonos.add(resul);
        adaptador.notifyDataSetChanged();


        SharedPreferences preferencias=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(telefonos);
        editor.putStringSet("key", set);
        editor.commit();



    }
}