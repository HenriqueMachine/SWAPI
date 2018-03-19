package com.example.henrique.starwarsapi.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.henrique.starwarsapi.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Button buttonPlanet;
    private Button buttonPeople;
    private Button buttonSpecies;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       buttonPeople = (Button) findViewById(R.id.button1);
       buttonPlanet = (Button) findViewById(R.id.button2);
       buttonSpecies = (Button) findViewById(R.id.button3);
       navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
       navigationView.setOnNavigationItemSelectedListener(this);

        buttonPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent people = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(people);

            }
        });

        buttonPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent planet = new Intent(MainActivity.this, PlanetsActivity.class);
                startActivity(planet);

            }
        });

        buttonSpecies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent specie = new Intent(MainActivity.this, SpeciesActivity.class);
                startActivity(specie);

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iconPrincipal:{
                    Intent intentPrincipal = new Intent(MainActivity.this, MainActivity.class);
                    finish();
                    startActivity(intentPrincipal);
                    break;
            }
            case R.id.iconCamera: {
                Intent intentPrincipal = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intentPrincipal);
                break;
            }

        }

        return true;
    }
}
