package com.example.henrique.starwarsapi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.henrique.starwarsapi.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlanet;
    private Button buttonPeople;

    /*private RecyclerView recyclerView;
    private RecyclerAdapterPlanets adapter;
    private List<Planet> list = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlanet);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       adapter = new RecyclerAdapterPlanets(list);
       recyclerView.setAdapter(adapter);*/

       buttonPeople = (Button) findViewById(R.id.button1);
        buttonPlanet = (Button) findViewById(R.id.button2);

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



    }

}
