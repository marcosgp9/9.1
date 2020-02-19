package com.example.a91;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner opciones;
    ArrayList<Planet> listaPlanetas;
    String[] nombrePlanetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertarPlanetesABaseDeDades();
        conseguirPlanetas();
        nombrePlanetas = conseguirNombrePlanetas(listaPlanetas);


        Spinner Slenguajes = (Spinner) findViewById(R.id.spinnerCategories);
        Slenguajes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,nombrePlanetas));


    }

    public void insertarPlanetesABaseDeDades(){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        admin.insertarPlanetas(BaseDeDatos);
    }

    public void conseguirPlanetas(){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        listaPlanetas = admin.conseguirTodosLosPlanetas();
    }

    public String[] conseguirNombrePlanetas(ArrayList<Planet> planetas){

        String[] planeta = new String[planetas.size()];

        for(int i = 0; i < planetas.size(); i++){
            planeta[i] = planetas.get(i).getName();
        }

        return planeta;

    }
}
