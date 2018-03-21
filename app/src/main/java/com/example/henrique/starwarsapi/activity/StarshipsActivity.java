package com.example.henrique.starwarsapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.adapters.RecycleAdapterStarships;
import com.example.henrique.starwarsapi.interfaces.SwapiService;
import com.example.henrique.starwarsapi.models.CallStarships;
import com.example.henrique.starwarsapi.models.Starships;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarshipsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Starships> starshipsList = new ArrayList<>();
    private int pageCurrent;
    private int auxCount;
    private int auxTotal;
    private int auxVisible;
    private boolean loading = true;
    private int previousTotal = 0;
    private RecycleAdapterStarships recycleAdapterStarships;
    private Starships fake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewStarship);
        recyclerView.setLayoutManager(linearLayoutManager);
        fake = new Starships();
        fake.type = 1;
        starshipsList.add(fake);
        recycleAdapterStarships = new RecycleAdapterStarships(starshipsList);
        recyclerView.setAdapter(recycleAdapterStarships);

        methodStarships(0);

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
                    if (auxTotal > previousTotal){
                        Log.i("E","if (auxTotal > previousTotal){} ");
                        loading = false;
                        previousTotal = auxTotal;
                    }
                }

                if (!loading && (auxTotal - 1) <= (auxVisible + 1)){
                    Log.i("E","if (! loading && (auxTotal - auxCount) == (auxVisible + showInScreen)){}");
                    Log.i("E","auxTotal = " + auxTotal + " || " + "auxCount = " + auxCount + " || " +"auxVisible = " + auxVisible + " || ");
                    pageCurrent++;
                    methodStarships(pageCurrent);
                    loading = true;
                }
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);
    }
    private void methodStarships(final int page){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SwapiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pageCurrent = page;

        final SwapiService service = retrofit.create(SwapiService.class);
        final Call<CallStarships> requestStr_Shp = service.listStarships(page);
        requestStr_Shp.enqueue(new Callback<CallStarships>() {
            @Override
            public void onResponse(Call<CallStarships> call, Response<CallStarships> response) {
                if (response.isSuccessful()){
                    starshipsList.remove(fake);

                    Log.i("E", "Callback Success... CODE: #" + response.code() + ".");
                    if (page == 0){
                        methodStarships(1);
                        CallStarships starships = response.body();
                        starshipsList = starships.results;
                        recyclerView.setAdapter(recycleAdapterStarships);
                    } else {
                        if (starshipsList != null){
                            CallStarships callStarships = response.body();
                            for (Starships starships : callStarships.results){
                                starshipsList.add(starships);
                            }
                            Log.i("E", "<- Fim do for -> " + starshipsList.size());
                            recycleAdapterStarships.notifyDataSetChanged();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<CallStarships> call, Throwable t) {
                Log.e("EE", "Error" + t.getMessage());

            }
        });
    }
}
