package com.example.henrique.starwarsapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.henrique.starwarsapi.adapters.RecycleAdapterPeople;
import com.example.henrique.starwarsapi.interfaces.SwapiService;
import com.example.henrique.starwarsapi.models.CallPeople;
import com.example.henrique.starwarsapi.models.People;
import com.example.henrique.starwarsapi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<People> people = new ArrayList<>();
    private int pageCurrent;
    private int auxCount;
    private int auxTotal;
    private int auxVisible;
    private boolean loading = true;
    private int previousTotal = 0;
    private RecycleAdapterPeople recyclerAdapterPeople;
    private People people1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPeople);
        recyclerView.setLayoutManager(linearLayoutManager);
        people1 = new People();
        people1.type = 1;
        people.add(people1);
        recyclerAdapterPeople = new RecycleAdapterPeople(people, PeopleActivity.this);
        recyclerView.setAdapter(recyclerAdapterPeople);

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
                if (! loading && (auxTotal - 2) <= (auxVisible + 1 )){
                    Log.i("E","if (! loading && (auxTotal - auxCount) == (auxVisible + showInScreen)){}");
                    Log.i("E","auxTotal = " + auxTotal + " || " + "auxCount = " + auxCount + " || " +"auxVisible = " + auxVisible + " || ");
                    pageCurrent ++;
                    methodPlanets(pageCurrent);
                    Toast.makeText(PeopleActivity.this, "Page " + pageCurrent, Toast.LENGTH_SHORT).show();
                    loading = true;
                }
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);
    }
    private void methodPlanets(final int pagePeoples) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SwapiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pageCurrent = pagePeoples;

        //polymorphism
        final SwapiService service = retrofit.create(SwapiService.class);
        final Call<CallPeople> requestPlanet = service.listPeoples(pagePeoples);
        requestPlanet.enqueue(new Callback<CallPeople>() {

            @Override
            public void onResponse(Call<CallPeople> call, Response<CallPeople> response) {
                if (response.isSuccessful()) {
                    people.remove(people1);

                    Log.i("E", "Callback Success... CODE: #" + response.code() + ".");
                    if (pagePeoples == 0) {
                        methodPlanets(1);
                        CallPeople list = response.body();
                        people = list.results;
                        recyclerView.setAdapter(recyclerAdapterPeople);
                    } else {
                        if (people != null) {
                            CallPeople list = response.body();
                            for (People people1 : list.results) {
                               people.add(people1);
                            }
                            Log.i("E", "<- Fim do for -> " + people.size());
                            recyclerAdapterPeople.notifyDataSetChanged();
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
            public void onFailure(Call<CallPeople> call, Throwable t) {
                Log.e("EE", "Error" + t.getMessage());
            }
        });
    }
}