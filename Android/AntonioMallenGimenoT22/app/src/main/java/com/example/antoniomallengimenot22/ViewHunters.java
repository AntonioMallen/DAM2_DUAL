package com.example.antoniomallengimenot22;

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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ViewHunters extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText nombre;
    private ListView lista;
    private ArrayList hunters;
    private ArrayAdapter adaptador;
    private int opcion;
    Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hunters);

        nombre= (EditText) findViewById(R.id.nombreView);
        lista= (ListView) findViewById(R.id.linear);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        SharedPreferences prefe=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        set = prefe.getStringSet("key", null);
        set.add("Test");
        hunters=new ArrayList<String>(set);


        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hunters);

        lista.setAdapter(adaptador);


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewHunters.this);

                builder.setTitle("Eliminar");
                builder.setMessage("¿Quieres eliminar este hunter?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        hunters.remove(i);
                        adaptador.notifyDataSetChanged();
                        SharedPreferences preferencias=getSharedPreferences("agenda", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferencias.edit();
                        Set<String> set = new HashSet<String>();
                        set.addAll(hunters);
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


        if(opcion==0){
            //añadir objeto de tipo hunter con imagen de Droid
        }else if(opcion==1){
            //añadir objeto de tipo hunter con imagen de Mandalorian
        }else{
            //añadir objeto de tipo hunter con imagen de trandoshan
        }
        hunters.add(nom);
        adaptador.notifyDataSetChanged();


        SharedPreferences preferencias=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        Set<String> set = new HashSet<String>(); // envede de string de tipo hunter
        set.addAll(hunters);
        editor.putStringSet("key", set);
        editor.commit();



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        opcion=i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        opcion=0;
    }
}