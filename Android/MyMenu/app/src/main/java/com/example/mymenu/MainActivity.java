package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String mensaje ="";
        switch (item.getItemId()){
            case R.id.MnOp1:

                return true;
            case R.id.MnOp2:

                return true;
            case R.id.MnOp3:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}