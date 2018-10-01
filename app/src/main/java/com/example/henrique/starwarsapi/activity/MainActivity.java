package com.example.henrique.starwarsapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.henrique.starwarsapi.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPeople = findViewById(R.id.button1);
        Button buttonPlanet = findViewById(R.id.button2);
        Button buttonSpecies = findViewById(R.id.button3);
        Button buttonStarS = findViewById(R.id.button4);

        buttonStarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarshipsActivity.class);
                startActivity(intent);
            }
        });

        buttonPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });

        buttonPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PlanetsActivity.class);
                startActivity(intent);
            }
        });

        buttonSpecies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SpeciesActivity.class);
                startActivity(intent);
            }
        });

    }


}