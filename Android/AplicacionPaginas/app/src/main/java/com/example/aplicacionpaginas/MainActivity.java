package com.example.aplicacionpaginas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ViewPager view1;
    private LinearLayout pagina1;
    private ConstraintLayout pagina2, pagina3;
    private ArrayList<Button> listaBotones;
    private int numeroAleatorio;
    private EditText number;
    private TextView score;
    private int aciertos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view1 = (ViewPager) findViewById(R.id.view);
        view1.setAdapter(new AdminPageAdapter());
    }
    public void irPagina1(View v) {
        view1.setCurrentItem(0);
    }
    public void irPagina2(View v) {
        view1.setCurrentItem(1);
    }
    public void irPagina3(View v) {
        view1.setCurrentItem(2);
    }

    public void cargarBuscadores() {
        final String[] sitios = {"google.com", "yahoo.com", "bing.com"};
        ListView lista = pagina1.findViewById(R.id.lista);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sitios);

        lista.setAdapter(adaptador1);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int i,long l) {
                Intent intento1=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www."+sitios[i]));
                startActivity(intento1);
            }
        });



    }

    public void cargarBotones() {
        LinearLayout botones = pagina2.findViewById(R.id.botones);
        listaBotones=new ArrayList<Button>();
        for(int i =0;i<5;i++){
            Button button = new Button(this);
            //button.setLayoutParams(lp);
            Random random = new Random();
            button.setBackgroundColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setBackgroundColor(Color.WHITE);
                }
            });
            listaBotones.add(button);
            botones.addView(button);
        }
    }


    public void reset(View v) {
        for(Button boton: listaBotones){
            Random random = new Random();
            boton.setBackgroundColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }

    }

    private void cargarNumerosAleatorios(){
        Random random = new Random();
        numeroAleatorio=random.nextInt(50);
        Toast toast1 = Toast.makeText(getApplicationContext(),
                ""+numeroAleatorio, Toast.LENGTH_SHORT);
        toast1.show();

    }
    public void check(View v){
        number=(EditText)pagina3.findViewById(R.id.number);
        score=(TextView)pagina3.findViewById(R.id.score);
        int valor= Integer.parseInt(number.getText().toString());

        if(valor==numeroAleatorio){
            aciertos++;
            score.setText(String.valueOf(aciertos));
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "HAS ACERTADO!!", Toast.LENGTH_SHORT);
            toast1.show();
        }
        else if(valor<numeroAleatorio){
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "El numero es mayor", Toast.LENGTH_SHORT);
            toast1.show();
        }
        else if(valor>numeroAleatorio){
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "El numero es menor", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }


    class AdminPageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(View collection, int position, Object
                view) {
            ((ViewPager) collection).removeView((View) view);
        }


        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            View paginaactual = null;
            switch (position) {
                case 0:
                    if (pagina1 == null) {
                        pagina1 = (LinearLayout)
                                LayoutInflater.from(MainActivity.this).inflate(R.layout.pagina1, null);
                        cargarBuscadores();
                    }
                    paginaactual = pagina1;
                    break;
                case 1:
                    if (pagina2 == null) {
                        pagina2 = (ConstraintLayout)
                                LayoutInflater.from(MainActivity.this).inflate(R.layout.pagina2, null);
                        cargarBotones();

                    }
                    paginaactual = pagina2;
                    break;
                case 2:
                    if (pagina3 == null) {
                        pagina3 = (ConstraintLayout)
                                LayoutInflater.from(MainActivity.this).inflate(R.layout.pagina3, null);
                        cargarNumerosAleatorios();

                    }
                    paginaactual = pagina3;
                    break;
            }
            ViewPager vp = (ViewPager) collection;

            vp.addView(paginaactual, 0);
            return paginaactual;
        }


    }
}



