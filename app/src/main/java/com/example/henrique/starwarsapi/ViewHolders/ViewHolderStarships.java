package com.example.henrique.starwarsapi.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.henrique.starwarsapi.R;

/**
 * Created by henrique on 21/03/18.
 */

public class ViewHolderStarships extends RecyclerView.ViewHolder {

    public TextView starShipsName;
    public TextView starShipsModel;
    public TextView starShipsManufacturer;
    public TextView starShipsCost;
    public TextView starShipsMGLT;
    public TextView starShipsCrew;
    public TextView starShipsHypDriverRate;

    public ViewHolderStarships(View itemView, int viewType) {
        super(itemView);
        if (viewType == 1){

        }else if (viewType == 0){

            this.starShipsName = itemView.findViewById(R.id.titleStartship);
            this.starShipsModel = itemView.findViewById(R.id.modelStar);
            this.starShipsManufacturer = itemView.findViewById(R.id.manufacturerStar);
            this.starShipsCost = itemView.findViewById(R.id.costStar);
            this.starShipsMGLT = itemView.findViewById(R.id.MGLTstar);
            this.starShipsCrew = itemView.findViewById(R.id.crewStar);
            this.starShipsHypDriverRate = itemView.findViewById(R.id.hyperdriveRateStar);

        }
    }
}
