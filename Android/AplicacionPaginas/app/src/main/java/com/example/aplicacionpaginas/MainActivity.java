package com.example.aplicacionpaginas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ViewPager view1;
    LinearLayout pagina1, pagina2, pagina3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = (ViewPager) findViewById(R.id.view);
        view1.setAdapter(new AdminPageAdapter());
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
//carga la vista

//carga el método cargarBotones

                    }
                    paginaactual = pagina2;
                    break;
                case 2:
                    if (pagina3 == null) {
                        pagina3 = (LinearLayout)
                                LayoutInflater.from(MainActivity.this).inflate(R.layout.pagina3, null);
                        cargarBuscadores();

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



