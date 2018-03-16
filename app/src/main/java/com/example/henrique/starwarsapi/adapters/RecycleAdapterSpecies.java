package com.example.henrique.starwarsapi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderSpecies;
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
    public void onBindViewHolder(final ViewHolderSpecies holder, final int position) {

        final Species species = speciesList.get(position);

        if (species.type == 1){
            Log.i("E", "Type 1 specie");
        }else if(species.type == 0){
            Log.i("E","Type 0 specie");

            String language = String.format("Language: %s.", species.language);
            String classification = String.format("Classification: %s.", species.classification);
            String skin_color = String.format("Skin Color: %s.", species.skin_colors);
            String eye_colors = String.format("Eye Color: %s.", species.eye_colors);
            String homeWorld = String.format("HomeWorld: %s.", species.homeworld);
            String designation = String.format("Designation: %s.", species.designation);
            String average_lifespan = String.format("Average Lifespan: %s.", species.average_lifespan);

            holder.name.setText(species.name);
            holder.language.setText(language);
            holder.classification.setText(classification);
            holder.eye_colors.setText(eye_colors);
            holder.skin_colors.setText(skin_color);
            holder.homeWorldSpecies.setText(homeWorld);
            holder.designationSpecies.setText(designation);
            holder.average_lifespan.setText(average_lifespan);

            holder.btn_Species.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Clickou !!!", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return speciesList.size();
    }
}
