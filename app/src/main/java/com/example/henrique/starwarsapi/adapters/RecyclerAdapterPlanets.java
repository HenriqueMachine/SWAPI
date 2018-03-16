package com.example.henrique.starwarsapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.henrique.starwarsapi.utils.Const;
import com.example.henrique.starwarsapi.models.Planet;
import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderPlanets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henrique on 27/02/18.
 */

public class RecyclerAdapterPlanets extends RecyclerView.Adapter<ViewHolderPlanets> {

    private List<Planet> list;
    private Context mContextPlanet;
    private ArrayList residents = new ArrayList();

    public RecyclerAdapterPlanets(List<Planet> Planets, Context context) {
        this.list = Planets;
        this.mContextPlanet = context;
    }

    @Override
    public ViewHolderPlanets onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
        } else if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planet, parent, false);
        }
        return new ViewHolderPlanets(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        Planet planet = list.get(position);
        return planet.type;
    }
    @Override
    public void onBindViewHolder(ViewHolderPlanets holder, final int position) {

        final Planet planet = list.get(position);

        if(planet.type == 1){
            Log.i("E", "Type1");

        } else if (planet.type == 0) {
            Log.i("E", "Type 0 ");

            String population = String.format("Population: %s.", planet.population);
            String terrain = String.format("Terrain: %s.", planet.terrain);
            String gravity = String.format("Gravity: %s.", planet.gravity);
            String climate = String.format("Climate: %s.", planet.climate);
            String diameter = String.format("Diameter: %s.", planet.diameter);
            String surfaceWater = String.format("Surface Water: %s", planet.surface_water);
            String rotationP = String.format("Rotation Period: %s.  ", planet.rotation_period);
            String orbitalP = String.format("Orbital Period: %s. ", planet.orbital_period);

            holder.planetName.setText(planet.name);
            holder.rotation_period.setText(rotationP);
            holder.orbital_period.setText(orbitalP);
            holder.population.setText(population);
            holder.terrain.setText(terrain);
            holder.gravity.setText(gravity);
            holder.climate.setText(climate);
            holder.diameter.setText(diameter);
            holder.surfaceWater.setText(surfaceWater);

            holder.btn_residents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("E", "Clickou" + position + planet.residents);

                        if (residents != null){
                               Toast.makeText(mContextPlanet, "Shhhhh... nobody lives here :( ", Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
