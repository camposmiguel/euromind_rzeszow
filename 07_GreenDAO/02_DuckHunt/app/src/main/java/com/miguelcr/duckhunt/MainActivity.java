package com.miguelcr.duckhunt;

import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView duck;
    Random random;
    int counter = 0;
    TextView tvCounterDucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        duck = (ImageView)findViewById(R.id.duckImage);
        tvCounterDucks = (TextView) findViewById(R.id.countDucks);

        // Change the font type for the TextView tvCounterDucks
        Typeface type = Typeface.createFromAsset(getAssets(),"pixel.otf");
        tvCounterDucks.setTypeface(type);

        randomDuckPosition();
    }

    private void randomDuckPosition() {
        // Screen Size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        random = new Random();
        int randomX = random.nextInt(width-duck.getWidth() + 1);
        int randomY = random.nextInt(height-duck.getHeight() + 1);
        duck.setX(randomX);
        duck.setY(randomY);
    }

    public void duckClicked(View view) {
        counter++;
        tvCounterDucks.setText("Ducks: "+String.valueOf(counter));
        randomDuckPosition();
    }
}
