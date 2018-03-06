package com.example.henrique.starwarsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.starwarsapi.Models.CallPeople;
import com.example.henrique.starwarsapi.Models.people;

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

                    for(people pp : list.results){
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
