package com.miguelcr.customlistviewfruits;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textViewDuration, textViewDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewDuration = (TextView) findViewById(R.id.durationExerciseDetail);
        textViewDistance = (TextView) findViewById(R.id.distanceExerciseDetail);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        int duration = extras.getInt("duration");
        int distance = extras.getInt("distance");

        getSupportActionBar().setTitle(title);

        textViewDuration.setText(duration+"'");
        textViewDistance.setText(distance+" Km");

    }
}
