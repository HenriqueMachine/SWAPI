package com.example.henrique.starwarsapi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.henrique.starwarsapi.Interface.SwapiService;
import com.example.henrique.starwarsapi.Models.CallPeople;
import com.example.henrique.starwarsapi.Models.People;
import com.example.henrique.starwarsapi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SwapiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiService service = retrofit.create(SwapiService.class);
        Call<CallPeople> requestPeople = service.listPeoples();

        requestPeople.enqueue(new Callback<CallPeople>() {
            @Override
            public void onResponse(Call<CallPeople> call, Response<CallPeople> response) {

                if (!response.isSuccessful()){
                    Log.i("ES", "Callback error..." + response.code());
                }
                else{
                    CallPeople list = response.body();

                    for(People pp : list.results){
                        Log.i("E", String.format(" NAME: %s \n", pp.name));
                        Log.i("E", "***********");
                        String format = String.format(" NAME: %s \n", pp.name);
                    }
                }
            }
            @Override
            public void onFailure(Call<CallPeople> call, Throwable t) {
                Log.e("F", "Error" + t.getMessage());
            }
        });

    }
}
