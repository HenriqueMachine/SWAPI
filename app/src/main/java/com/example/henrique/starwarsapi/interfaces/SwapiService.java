package com.example.henrique.starwarsapi.interfaces;

import com.example.henrique.starwarsapi.models.CallPeople;
import com.example.henrique.starwarsapi.models.CallPlanet;
import com.example.henrique.starwarsapi.models.CallSpecies;
import com.example.henrique.starwarsapi.models.CallStarships;
import com.example.henrique.starwarsapi.models.CallVehicles;
import com.example.henrique.starwarsapi.models.Planet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by henrique on 23/02/18.
 * {parameter} parameter to change
 *(@Path(value = "parameter") String paramCalled );
 */

public interface SwapiService {

    String BASE_URL = "https://swapi.co/api/";

    @GET("planets/")
    Call<CallPlanet>listPlanets(@Query("page")int page);

    @GET("planets/{page}")
    Call<Planet> listPlanetsHome (@Path("page") int planetNumber);

    @GET("people/")
    Call<CallPeople>listPeoples(@Query("page")int pagePeople);

    @GET ("species/")
    Call<CallSpecies> listSpecies(@Query("page")int pageSpecie);

    @GET("starships/")
    Call<CallStarships> listStarships(@Query("page")int pageStarS);

    @GET ("vehicles/?page=1")
    Call<CallVehicles> listVehicles();
}
