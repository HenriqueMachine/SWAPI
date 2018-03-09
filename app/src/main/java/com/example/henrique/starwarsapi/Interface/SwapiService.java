package com.example.henrique.starwarsapi.Interface;

import com.example.henrique.starwarsapi.Models.CallPeople;
import com.example.henrique.starwarsapi.Models.CallPlanet;
import com.example.henrique.starwarsapi.Models.CallSpecies;
import com.example.henrique.starwarsapi.Models.CallStarships;
import com.example.henrique.starwarsapi.Models.CallVehicles;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * Created by henrique on 23/02/18.
 * {parameter} parameter to change
 *(@Path(value = "parameter") String paramCalled );
 */

public interface SwapiService {

    public static final String BASE_URL = "https://swapi.co/api/";

    @GET("planets/")
    Call<CallPlanet>listPlanets(@Query("page")int page);

    @GET("planets/")
    Call<CallPlanet> listPlanetsHome (@Query("search") int pageTest);

    @GET("people/")
    Call<CallPeople>listPeoples(@Query("page")int pagePeople);

    @GET("starships/?page=1")
    Call<CallStarships> listStarships();

    @GET ("vehicles/?page=1")
    Call<CallVehicles> listVehicles();

    @GET ("species/?page=1")
    Call<CallSpecies> listSpecies();

}
