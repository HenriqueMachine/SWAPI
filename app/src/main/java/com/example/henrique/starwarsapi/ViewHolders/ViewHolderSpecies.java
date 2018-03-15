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

    private TextView name;
    private TextView language;
    private TextView classification;
    private TextView skin_colors;
    private TextView eye_colors;
    private TextView homeWorldSpecies;
    private Button btn_Species;

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

        }

    }
}
