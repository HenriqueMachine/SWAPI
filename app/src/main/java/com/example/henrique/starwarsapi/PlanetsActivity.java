package com.example.henrique.starwarsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.starwarsapi.Models.CallPlanet;
import com.example.henrique.starwarsapi.Models.Planet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Planet> planetList = new ArrayList<>();
    private int pageCurrent;
    private int auxCount;
    private int auxTotal;
    private int auxVisible;
    private boolean loading = true;
    private int previousTotal = 0;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlanet);
        recyclerView.setLayoutManager(linearLayoutManager);
        Planet fake = new Planet();
        fake.type = 1;
        planetList.add(fake);
        recyclerAdapter = new RecyclerAdapter(planetList);
        recyclerView.setAdapter(recyclerAdapter);

        methodPlanets(0);

        RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                auxCount = recyclerView.getChildCount();
                auxTotal = linearLayoutManager.getItemCount();
                auxVisible = linearLayoutManager.findFirstVisibleItemPosition();

                Log.i("E", "*Contador = " + auxCount + " <//> " +"*Itens Visiveis = " + auxVisible + " <//> " +"*Total = " + auxTotal);
                Log.i("E", "===========================================");
                if (loading){
                    Log.i("E", "Loading ...");
                    if (auxTotal > previousTotal) {
                        Log.i("E","if (auxTotal > previousTotal){} ");
                        loading = false;
                        previousTotal = auxTotal;
                    }
                }
                //Condition to call the page when visible.
                if (! loading && (auxTotal - 1) <= (auxVisible + 1 )){
                    Log.i("E","if (! loading && (auxTotal - auxCount) == (auxVisible + showInScreen)){}");
                    Log.i("E","auxTotal = " + auxTotal + " || " + "auxCount = " + auxCount + " || " +"auxVisible = " + auxVisible + " || ");
                    pageCurrent ++;
                    methodPlanets(pageCurrent);
                    Toast.makeText(PlanetsActivity.this, "Page " + pageCurrent, Toast.LENGTH_SHORT).show();
                    loading = true;
                }
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);
    }
    private void methodPlanets(final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SwapiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pageCurrent = page;

        //polymorphism
        final SwapiService service = retrofit.create(SwapiService.class);
        final Call<CallPlanet> requestPlanet = service.listPlanets(page);
        requestPlanet.enqueue(new Callback<CallPlanet>() {
            @Override
            public void onResponse(Call<CallPlanet> call, Response<CallPlanet> response) {
                if (response.isSuccessful()) {
                    planetList.remove(0);
                    Log.i("E", "Callback Success... CODE: #" + response.code() + ".");
                    if (page == 0) {
                        methodPlanets(1);
                        CallPlanet list = response.body();
                        planetList = list.results;
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        if (planetList != null) {
                            CallPlanet list = response.body();
                            for (Planet Planet : list.results) {
                                planetList.add(Planet);
                            }
                            Log.i("E", "<- Fim do for -> " + planetList.size());
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                /**
                 for(Planet p : list.results ){
                 Log.i("E", String.format("NAME_PLANET: %s. \n", p.name));
                 Log.i("E", "---------------");
                 String aux = String.format("Name %s \n",p.name);
                 }
                 **/
            }
            @Override
            public void onFailure(Call<CallPlanet> call, Throwable t) {
                Log.e("EE", "Error" + t.getMessage());
            }
        });
    }
}