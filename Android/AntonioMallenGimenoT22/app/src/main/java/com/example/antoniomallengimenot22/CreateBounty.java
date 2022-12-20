package com.example.antoniomallengimenot22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CreateBounty extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private EditText nombre;
    private EditText age;
    private RadioButton sexo;
    private String specie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bounty);

        nombre=(EditText) findViewById(R.id.name);
        age=(EditText) findViewById(R.id.age);

        sexo=(RadioButton)findViewById(R.id.radioButton);
        if(!sexo.isChecked()){
            sexo=(RadioButton)findViewById(R.id.radioButton2);
            if(!sexo.isChecked()){
                sexo=(RadioButton)findViewById(R.id.radioButton3);
            }
        }

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.species, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }



    public void back(View view){
        Intent intent=new Intent(CreateBounty.this, SecondActivity.class);
        startActivity(intent);
    }

    public void post(View view){
        Intent intent=new Intent(CreateBounty.this, BountyData.class);
        String name = nombre.getText().toString();
        String edad = age.getText().toString();
        String sex = sexo.getText().toString();


        intent.putExtra("username",name);
        intent.putExtra("edad",edad);
        intent.putExtra("sex",sex);
        intent.putExtra("specie",specie);

        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0){
            specie="Humano";
        }else if(i==1){
            specie="Droid";
        }else{
            specie="Mandalorian";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}