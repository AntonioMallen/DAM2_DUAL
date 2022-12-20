package com.example.antoniomallengimenot22;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView texto;
    private Intent intent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        texto = (TextView) findViewById(R.id.nomb);

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        texto.setText(username);


    }

    public void view_hunters(View view){
        intent=new Intent(SecondActivity.this, ViewHunters.class);
        startActivity(intent);
    }
    public void create_bounty(View view){
        intent=new Intent(SecondActivity.this, CreateBounty.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.MnOp1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Do not ask");
                builder.setMessage("The less you know the better");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.MnOp2:
                Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(inte);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void exit(View view){
        intent=new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }



}
