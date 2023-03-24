package com.example.edittextantoniomallen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText email,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.email);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void verificar(View v) {
        String passwd=password.getText().toString();
        String user=username.getText().toString();
        if(passwd.length()<=0) {
            Toast.makeText(this, "Password can’t be empty", Toast.LENGTH_LONG).show();
        }
        if(user.length()<=0) {
            Toast.makeText(this, "El usuario no puede estar vacio", Toast.LENGTH_LONG).show();
        }
    }
}