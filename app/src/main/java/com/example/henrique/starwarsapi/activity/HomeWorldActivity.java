package com.example.henrique.starwarsapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.henrique.starwarsapi.adapters.RecyclerAdapterPlanets;
import com.example.henrique.starwarsapi.interfaces.SwapiService;
import com.example.henrique.starwarsapi.models.Planet;
import com.example.henrique.starwarsapi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.henrique.starwarsapi.interfaces.SwapiService.BASE_URL;

public class HomeWorldActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Planet> planetList = new ArrayList<>();
    private RecyclerAdapterPlanets recyclerAdapterPlanets;
    private Planet fake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_world);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlanetHomeW);
        recyclerView.setLayoutManager(linearLayoutManager);
        fake = new Planet();
        fake.type = 1;
        planetList.add(fake);
        recyclerAdapterPlanets = new RecyclerAdapterPlanets(planetList, HomeWorldActivity.this);
        recyclerView.setAdapter(recyclerAdapterPlanets);

        Intent it = getIntent();
        String stringURL = it.getStringExtra("Clickou");
        String[] cutURL = stringURL.split("/");
        String savePos = cutURL[5];
        Log.i("E", "StringPos >>> " + savePos);
        int savePosInt = Integer.valueOf(savePos);

        methodPlanets(savePosInt);
    }
    private void methodPlanets(final int planet) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //polymorphism
        final SwapiService service = retrofit.create(SwapiService.class);
        final Call<Planet> requestPlanet = service.listPlanetsHome(planet);
        requestPlanet.enqueue(new Callback<Planet>() {

            @Override
            public void onResponse(Call<Planet> call, Response<Planet> response) {
                if (response.isSuccessful()) {
                    planetList.remove(fake);
                    Log.i("E", "Callback Success... CODE: #" + response.code() + ".");
                    Planet planet1 = response.body();
                    planet1.type = 0;
                    planetList.add(planet1);
                    recyclerAdapterPlanets = new RecyclerAdapterPlanets(planetList, HomeWorldActivity.this);
                    recyclerView.setAdapter(recyclerAdapterPlanets);
                }
            }
            @Override
            public void onFailure(Call<Planet> call, Throwable t) {
                Log.e("EE", "Error" + t.getMessage());
            }
        });
    }
}