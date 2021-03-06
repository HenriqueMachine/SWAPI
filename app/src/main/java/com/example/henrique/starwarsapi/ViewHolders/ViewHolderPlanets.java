package com.example.henrique.starwarsapi.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.henrique.starwarsapi.R;

/**
 * Created by henrique on 27/02/18.
 */

public class ViewHolderPlanets extends RecyclerView.ViewHolder {

    public TextView planetName;
    public TextView population;
    public TextView climate;
    public TextView surfaceWater;
    public TextView gravity;
    public TextView diameter;
    public TextView terrain;
    public TextView orbital_period;
    public TextView rotation_period;
    public Button btn_residents;

    public ViewHolderPlanets(View itemView, int viewType ) {
        super(itemView);
        if (viewType == 1){

        }else if (viewType == 0 ) {
            this.orbital_period = itemView.findViewById(R.id.orbitalPlanet);
            this.rotation_period = itemView.findViewById(R.id.rotationPlanet);
            this.planetName = itemView.findViewById(R.id.titlePlanet);
            this.population = itemView.findViewById(R.id.populationPlanet);
            this.climate = itemView.findViewById(R.id.climatePlanet);
            this.surfaceWater = itemView.findViewById(R.id.surfaceWaterPlanet);
            this.diameter = itemView.findViewById(R.id.diameterPlanet);
            this.gravity = itemView.findViewById(R.id.gravityPlanet);
            this.terrain = itemView.findViewById(R.id.terrainPlanet);
            this.btn_residents = itemView.findViewById(R.id.btnResident);
        }
    }
}
