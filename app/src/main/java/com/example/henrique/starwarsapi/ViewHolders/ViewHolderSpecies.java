package com.example.henrique.starwarsapi.ViewHolders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.henrique.starwarsapi.R;

/**
 * Created by henrique on 15/03/18.
 */

public class ViewHolderSpecies extends ViewHolder{

    public TextView name;
    public TextView language;
    public TextView classification;
    public TextView skin_colors;
    public TextView eye_colors;
    public TextView homeWorldSpecies;
    public TextView designationSpecies;
    public TextView average_lifespan;
    public Button btn_Species;

    public ViewHolderSpecies(View itemView, int viewType){
        super(itemView);

        if (viewType == 1){

        }else if (viewType == 0){

            this.btn_Species = itemView.findViewById(R.id.btnSpecies);
            this.name = itemView.findViewById(R.id.titleSpecies);
            this.language = itemView.findViewById(R.id.languageSpecie);
            this.classification = itemView.findViewById(R.id.classificationSpecies);
            this.skin_colors = itemView.findViewById(R.id.skinColorSpecies);
            this.eye_colors = itemView.findViewById(R.id.eyeColorSpecies);
            this.homeWorldSpecies = itemView.findViewById(R.id.homeWorldSpecies);
            this.designationSpecies = itemView.findViewById(R.id.designationSpecies);
            this.average_lifespan = itemView.findViewById(R.id.averagelifespanSpecies);

        }

    }
}
