package com.example.henrique.starwarsapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.henrique.starwarsapi.R;
import com.example.henrique.starwarsapi.utils.Const;

import java.util.ArrayList;

import static com.example.henrique.starwarsapi.utils.Const.*;

public class ResidentsActivity extends AppCompatActivity {
    private Intent residentsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents);

        Intent it = getIntent();
        residentsArray  = it.putStringArrayListExtra(Const.Params.RESIDENTS, null);
        Log.i("E", ">>>" + residentsArray);
    }
}
