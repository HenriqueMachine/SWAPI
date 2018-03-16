package com.example.henrique.starwarsapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.starwarsapi.adapters.RecycleAdapterSpecies;
import com.example.henrique.starwarsapi.interfaces.SwapiService;
import com.example.henrique.starwarsapi.models.CallSpecies;
import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.models.Species;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeciesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Species> speciesArrayList = new ArrayList<>();
    private int pageCurrent;
    private int auxCount;
    private int auxTotal;
    private int auxVisible;
    private boolean loading = true;
    private int previousTotal = 0;
    private RecycleAdapterSpecies recyclerAdapterSpecies;
    private Species species1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSpecie);
        recyclerView.setLayoutManager(linearLayoutManager);
        species1 = new Species();
        species1.type = 1;
        speciesArrayList.add(species1);
        recyclerAdapterSpecies = new RecycleAdapterSpecies(speciesArrayList, SpeciesActivity.this);
        recyclerView.setAdapter(recyclerAdapterSpecies);

        methosSpecies(0);

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
                if (! loading && (auxTotal - 2) <= (auxVisible + 1 )){
                    Log.i("E","if (! loading && (auxTotal - auxCount) == (auxVisible + showInScreen)){}");
                    Log.i("E","auxTotal = " + auxTotal + " || " + "auxCount = " + auxCount + " || " +"auxVisible = " + auxVisible + " || ");
                    pageCurrent ++;
                    methosSpecies(pageCurrent);
                    Toast.makeText(SpeciesActivity.this, "Page " + pageCurrent, Toast.LENGTH_SHORT).show();
                    loading = true;
                }
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);
    }
    private void methosSpecies(final int pageSpecies) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SwapiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pageCurrent = pageSpecies;

        //polymorphism
        final SwapiService service = retrofit.create(SwapiService.class);
        final Call<CallSpecies> requestSpecie = service.listSpecies(pageSpecies);
        requestSpecie.enqueue(new Callback<CallSpecies>() {

            @Override
            public void onResponse(Call<CallSpecies> call, Response<CallSpecies> response) {
                if (response.isSuccessful()) {
                    speciesArrayList.remove(species1);

                    Log.i("E", "Callback Success... CODE: #" + response.code() + ".");
                    if (pageSpecies == 0) {
                        methosSpecies(1);
                        CallSpecies list = response.body();
                        speciesArrayList = list.results;
                        recyclerView.setAdapter(recyclerAdapterSpecies);
                    } else {
                        if (speciesArrayList != null) {
                            CallSpecies list = response.body();
                            for (Species species : list.results) {
                                speciesArrayList.add(species);
                            }
                            Log.i("E", "<- Fim do for -> " + speciesArrayList.size());
                            recyclerAdapterSpecies.notifyDataSetChanged();
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
            public void onFailure(Call<CallSpecies> call, Throwable t) {
                Log.e("EE", "Error" + t.getMessage());
            }
        });
    }
}