package com.example.henrique.starwarsapi.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderStarships;
import com.example.henrique.starwarsapi.models.Starships;

import java.util.List;

/**
 * Created by henrique on 21/03/18.
 */

public class RecycleAdapterStarships extends RecyclerView.Adapter<ViewHolderStarships>{

    private List<Starships> list;
    public RecycleAdapterStarships(List<Starships> Starships){
        this.list = Starships;
    }

    @Override
    public ViewHolderStarships onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
        } else if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_starship, parent, false);
        }

        return new ViewHolderStarships(view, viewType);
    }

    @Override
    public int getItemViewType(int position){
        Starships starships = list.get(position);
        return starships.type;
    }

    @Override
    public void onBindViewHolder(ViewHolderStarships holder, int position) {

        final Starships starships = list.get(position);

        if (starships.type == 1 ){

        }else if (starships.type == 0){

            String model = String.format("Model: %s.", starships.model);
            String hyperDrive = String.format("HDR(HyperDriveRate): %s.",starships.hyperdrive_rating);
            String crew = String.format("Crew: %s.", starships.crew);
            String MGLT = String.format("MGLT(MEGALITH): %s.", starships.MGLT);
            String cost = String.format("Cost in credits($$): %s.", starships.cost_in_credits);
            String manufacturer = String.format("Manufacturer: %s.", starships.manufacturer);

            holder.starShipsName.setText(starships.name);
            holder.starShipsModel.setText(model);
            holder.starShipsHypDriverRate.setText(hyperDrive);
            holder.starShipsCrew.setText(crew);
            holder.starShipsMGLT.setText(MGLT);
            holder.starShipsCost.setText(cost);
            holder.starShipsManufacturer.setText(manufacturer);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
