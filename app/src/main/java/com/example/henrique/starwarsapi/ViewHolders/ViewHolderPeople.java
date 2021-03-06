package com.example.henrique.starwarsapi.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.henrique.starwarsapi.R;

/**
 * Created by henrique on 08/03/18.
 */

public class ViewHolderPeople extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView gender;
    public TextView birth_year;
    public TextView skin_color;
    public TextView hair_color;
    public TextView eye_color;
    public Button btnHome_World;

    public ViewHolderPeople(View itemView, int viewType) {
        super(itemView);

        if (viewType == 1){

        }else if (viewType == 0){

            this.btnHome_World = itemView.findViewById(R.id.btnHomeWorld);
            this.name = itemView.findViewById(R.id.titleName);
            this.gender = itemView.findViewById(R.id.gender);
            this.birth_year = itemView.findViewById(R.id.birthYear);
            this.skin_color = itemView.findViewById(R.id.skinColor);
            this.hair_color = itemView.findViewById(R.id.hairColor);
            this.eye_color = itemView.findViewById(R.id.eyeColor);
        }
    }
}
