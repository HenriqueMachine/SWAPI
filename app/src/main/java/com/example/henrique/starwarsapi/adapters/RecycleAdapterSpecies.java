package com.example.henrique.starwarsapi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderSpecies;
import com.example.henrique.starwarsapi.models.People;
import com.example.henrique.starwarsapi.models.Species;

import java.util.List;

/**
 * Created by henrique on 15/03/18.
 */

public class RecycleAdapterSpecies extends RecyclerView.Adapter<ViewHolderSpecies> {
    private List<Species> speciesList;
    private Context mContext;

    public RecycleAdapterSpecies(List<Species> species, Context context){
        this.speciesList = species;
        this.mContext = context;
    }

    @Override
    public ViewHolderSpecies onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
        }else if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_species, parent, false);
        }
        return new ViewHolderSpecies(view,viewType);
    }

    @Override
    public int getItemViewType(int position) {
        Species species = speciesList.get(position);
        return species.type;
    }

    @Override
    public void onBindViewHolder(ViewHolderSpecies holder, final int position) {

        final Species species = speciesList.get(position);

        if (species.type == 1){
            Log.i("E", "Type 1 specie");
        }else if(species.type == 0){
            Log.i("E","Type 0 specie");

            String language = String.format("Language: %s.", species.language);
            String classification = String.format("Classification: %s.", species.classification);
            String skin_color = String.format("Skin Color: %s.", species.skin_colors);
            String eye_colors = String.format("Language: %s.", species.language);
            String homeWorld = String.format("Language: %s.", species.language);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
