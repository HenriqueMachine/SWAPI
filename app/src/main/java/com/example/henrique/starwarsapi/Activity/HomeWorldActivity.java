package com.example.henrique.starwarsapi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.henrique.starwarsapi.Adapters.RecycleAdapterPeople;
import com.example.henrique.starwarsapi.Adapters.RecyclerAdapterPlanets;
import com.example.henrique.starwarsapi.Interface.SwapiService;
import com.example.henrique.starwarsapi.Models.CallPlanet;
import com.example.henrique.starwarsapi.Models.Planet;
import com.example.henrique.starwarsapi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeWorldActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Planet> planetList = new ArrayList<>();
    private RecyclerAdapterPlanets recyclerAdapterPlanets;
    private Planet type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_world);

        Intent it = getIntent();
        final String URL = it.getStringExtra("Clickou");

        type = new Planet();
        type.type=0;
        planetList.add(type);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlanetHomeW);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapterPlanets = new RecyclerAdapterPlanets(planetList);
        recyclerView.setAdapter(recyclerAdapterPlanets);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiService service = retrofit.create(SwapiService.class);
        Call<CallPlanet> requestPlanetButton = service.listPlanetsHome(URL);
        requestPlanetButton.enqueue(new Callback<CallPlanet>() {
            @Override
            public void onResponse(Call<CallPlanet> call, Response<CallPlanet> response) {
                CallPlanet list = response.body();
                planetList = list.results;
                planetList.add(type);
                recyclerView.setAdapter(recyclerAdapterPlanets);
                Log.i("E", "Chegou");
            }

            @Override
            public void onFailure(Call<CallPlanet> call, Throwable t) {

                Log.i("E", "Failure");

            }
        });

    }
}
