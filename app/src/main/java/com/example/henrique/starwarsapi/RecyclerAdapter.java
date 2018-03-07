package com.example.henrique.starwarsapi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.henrique.starwarsapi.Models.Planet;

import java.util.List;

/**
 * Created by henrique on 27/02/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Planet> list;
    public RecyclerAdapter(List<Planet> Planets){
        this.list = Planets;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        }else if (viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        Planet planet = list.get(position);
        return super.getItemViewType(planet.type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Planet planet = list.get(position);

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
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
