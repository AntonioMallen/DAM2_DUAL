package com.example.antonioud219_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText id,descripcion,precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.id);
        descripcion = (EditText) findViewById(R.id.descripcion);
        precio = (EditText) findViewById(R.id.precio);
    }

    public void alta(View view){
        int id = Integer.parseInt( this.id.getText().toString());
        String descripcion = this.descripcion.getText().toString();
        double precio = Double.parseDouble( this.precio.getText().toString());
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("codigo",id);
        cv.put("descripcion",descripcion);
        cv.put("precio",precio);
        bd.insert("Articulos",null,cv);
    }

    public void consultaporcodigo(View view){
        int id =0;
        id= Integer.parseInt( this.id.getText().toString());
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select descripcion, precio from Articulos where codigo=" + id, null);
        if (fila.moveToFirst()) {
            this.descripcion.setText(fila.getString(0));
            this.precio.setText(fila.getString(1));
        } else {
            Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
        }
        bd.close();

    }
    public void consultapordescripcion(View view){

        String descripcion ="";
        descripcion= this.descripcion.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select codigo, precio from Articulos where descripcion='"+descripcion+"'", null);
        if (fila.moveToFirst()) {
            this.id.setText(fila.getString(0));
            this.precio.setText(fila.getString(1));
        } else{
            Toast.makeText(this, "No existe un artículo con dicho código",Toast.LENGTH_SHORT).show(); }
        bd.close();
    }
    public void bajaporcodigo(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        int id =0;
        id= Integer.parseInt( this.id.getText().toString());
        int cant = bd.delete("Articulos", "codigo="+id , null);
        bd.close();
        this.id.setText("");
        this.descripcion.setText("");
        this.precio.setText("");
        if(cant==0){
            Toast.makeText(this, "No existe un artículo con dicho código",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "articulo eliminado",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View view){
        int id = Integer.parseInt( this.id.getText().toString());
        String descripcion = this.descripcion.getText().toString();
        double precio = Double.parseDouble( this.precio.getText().toString());
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("codigo",id);
        cv.put("descripcion",descripcion);
        cv.put("precio",precio);

        int cant = bd.update("Articulos", cv, "codigo="+id, null);
        bd.close();
        if(cant==0){
            Toast.makeText(this, "No existe un artículo con dicho código",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Se ha actualizado el articulo",Toast.LENGTH_SHORT).show();
        }
    }
}