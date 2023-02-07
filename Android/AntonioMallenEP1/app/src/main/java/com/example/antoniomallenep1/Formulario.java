package com.example.antoniomallenep1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Formulario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Formulario extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText correo;
    private CheckBox trabajo;
    private CheckBox personal;

    public Formulario() {
        // Required empty public constructor
    }

    public static Formulario newInstance(){
        return new Formulario();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override


    /**
     * Este metodo se ejecutara una vez cargue el frame.
     *
     * Primero asignamos a todas las variables creadas anteriormente a sus respectivos
     * componentes, tras esto crearemos un listener para que el boton de guardar ejecute el metodo
     * creado llamado "guardar", esto lo hago asi ya que al estar en un frame
     * no me deja llamarlo a traves de el View view y el onClick.
     *
     * Tras esto nos meteremos en el SharedPreferences y obtendremos los valores de las checkbox,
     * tras esto se los pondremos a estas.
     *
     * Luego abriremos el archivo y leeremos el texto que hubiesemos guardado
     * en una ejecucion anterior, tras esto se lo asignamos al campo de Correo Electronico
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_formulario, container, false);

        /*
        Asignacion diferentes variables
         */
        Button button2 = view.findViewById(R.id.button2);
        correo= (EditText) view.findViewById(R.id.correo);
        trabajo = view.findViewById(R.id.checkBox);
        personal = view.findViewById(R.id.checkBox2);

        /*
        Asignacion del metodo guardar al boton
         */
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardar();
            }
        });

        /*
        Cargado de checkbox
         */
        SharedPreferences pref = this.getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        Boolean personal = pref.getBoolean("perso",false);
        Boolean trabajo = pref.getBoolean("trab",false);
        this.trabajo.setChecked(trabajo);
        this.personal.setChecked(personal);


        /*
        Cargado de editText
         */
        String dato="";
        try {
            InputStreamReader archivo = new InputStreamReader(this.getActivity().openFileInput("mail.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            while(linea!=null){
                dato+=linea;
                linea = br.readLine();
            }
            correo.setText(dato);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }


    /**
     * La primera parte del metodo guardar cogera el texto del EditText y
     * lo guardara en un archivo llamado "mail.txt".
     *
     * Tras esto se mirara si las checkbox estan marcadas y en caso de que si lo esten
     * se almacenara en el SharedPreferences con la clave "MyPref".
     * Tras esto el programa hara un commit para que se acaben de almacenar y un
     * System.exit(0) para cerrar la aplicacion.
     */
    public void guardar (){
        /*
        Guardado de editText
         */
        String texto = correo.getText().toString();
        OutputStreamWriter archivo =null;
        try {
            archivo = new OutputStreamWriter(this.getActivity().openFileOutput(
                    "mail.txt", Activity.MODE_PRIVATE));
            archivo.write(texto);
            archivo.flush();
            archivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Guardado de checkbox
         */
        Boolean personal = this.personal.isChecked();
        Boolean trabajo = this.trabajo.isChecked();
        SharedPreferences pref = this.getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("perso",personal);
        editor.putBoolean("trab",trabajo);
        editor.commit();
        System.exit(0); // cerrar la aplicacion
    }
}