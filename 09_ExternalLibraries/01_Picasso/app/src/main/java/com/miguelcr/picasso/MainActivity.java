package com.miguelcr.picasso;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView starwarsTroop;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starwarsTroop = (ImageView) findViewById(R.id.ivStarWars);
        title = (TextView) findViewById(R.id.title);

        Picasso.with(MainActivity.this)
                .load("https://milnersblog.files.wordpress.com/2015/09/battle-stormtrooper-star-wars-ep7-the-force-awakens-characters-cut-out-with-transparent-background_31.png")
                //.resize(200, 200)
                .placeholder(R.drawable.ic_loading)
                .into(starwarsTroop);

        Typeface fontStarWars = Typeface.createFromAsset(getAssets(),"star_wars.ttf");
        title.setTypeface(fontStarWars);
    }
}