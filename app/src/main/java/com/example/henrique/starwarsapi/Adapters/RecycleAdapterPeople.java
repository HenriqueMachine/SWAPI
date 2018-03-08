package com.example.henrique.starwarsapi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henrique.starwarsapi.Models.People;
import com.example.henrique.starwarsapi.Models.Planet;
import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderPeople;
import com.example.henrique.starwarsapi.ViewHolders.ViewHolderPlanets;

import java.util.List;

/**
 * Created by henrique on 08/03/18.
 */

public class RecycleAdapterPeople extends RecyclerView.Adapter<ViewHolderPeople> {

    private List<People> peopleList;

    public RecycleAdapterPeople(List<People> People){
        this.peopleList = People;
    }

    @Override
    public ViewHolderPeople onCreateViewHolder (ViewGroup parent, int viewType){
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load, parent, false);
        } else if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        }
        return new ViewHolderPeople(view, viewType);
    }
    @Override
    public int getItemViewType(int position) {
        People people = peopleList.get(position);
        return people.type;
    }
    @Override
    public void onBindViewHolder(ViewHolderPeople holder, int position) {

        People people = peopleList.get(position);

        if(people.type == 1){
            Log.i("E", "Type1");

        } else if (people.type == 0) {
            Log.i("E", "Type 0 ");

            String gender = String.format("Gender: %s.", people.gender);
            String birthYear = String.format("Birth Year: %s.", people.birth_year);
            String homeWorld = String.format("Home World: %s.", people.homeworld);
            String skinColor = String.format("Skin Color: %s.", people.skin_color);

            holder.name.setText(people.name);
            holder.gender.setText(gender);
            holder.birth_year.setText(birthYear);
            holder.skin_color.setText(skinColor);
            holder.homeworld.setText(homeWorld);

        }
    }
    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}


