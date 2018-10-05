package com.nowrunning.travelrun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nowrunning.travelrun.adapter.CircleAdapter;

import java.util.ArrayList;

public class CircleActivity extends AbsActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.CircRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //layoutManager.setReverseLayout(true);
    rv.setLayoutManager(layoutManager);
        ArrayList pulledCircle = (OwnerProfile.getCircList());
        CircleAdapter adapter = new CircleAdapter(pulledCircle, this);
        rv.setAdapter(adapter);

        /*LinearLayout mainLayout = (LinearLayout)findViewById(R.id.circleProfileImages);

        //mainLayout = ;

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(20,20);

        ImageView imgIn = new ImageView(this);

        imgIn.setImageResource(R.drawable.pexels_photo_91227);

        mainLayout.addView(imgIn,imgParams);*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
