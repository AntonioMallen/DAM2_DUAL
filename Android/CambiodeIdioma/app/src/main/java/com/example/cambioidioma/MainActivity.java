package com.example.cambioidioma;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_LANGUAGE =
            "Locale.Helper.Selected.Language";
    private Context context;
    private TextView texto1;
    private TextView texto2;
    private Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto1=(TextView) findViewById(R.id.textView);
        texto2=(TextView) findViewById(R.id.textView2);
    }

        // the method is used to set the language at runtime
        public static Context setLocale(Context context, String language) {
            persist(context, language);
// updating the language for devices above android nougat
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return updateResources(context, language);
            }
// for devices having lower version of android os
            return updateResourcesLegacy(context, language);
        }

        private static void persist(Context context, String language) {
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SELECTED_LANGUAGE, language);
            editor.apply();
        }

        // the method is used update the language of application by creating
// object of inbuilt Locale class and passing language argument to it
        @TargetApi(Build.VERSION_CODES.N)
        private static Context updateResources(Context context, String
                language) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration configuration =
                    context.getResources().getConfiguration();
            configuration.setLocale(locale);

            configuration.setLayoutDirection(locale);
            return context.createConfigurationContext(configuration);
        }


        @SuppressWarnings("deprecation")
        private static Context updateResourcesLegacy(Context context, String
                language) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.locale = locale;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLayoutDirection(locale);
            }
            resources.updateConfiguration(configuration,
                    resources.getDisplayMetrics());
            return context;
        }

        public void es(View view){
            context = setLocale(MainActivity.this, "es");
            resources = context.getResources();
            setTitle(resources.getString(R.string.app_name));
            texto1.setText(resources.getString(R.string.saludo));
            texto2.setText(resources.getString(R.string.person));
        }

        public void en(View view){
            context = setLocale(MainActivity.this, "en");
            resources = context.getResources();
            setTitle(resources.getString(R.string.app_name));
            texto1.setText(resources.getString(R.string.saludo));
            texto2.setText(resources.getString(R.string.person));
        }
}


