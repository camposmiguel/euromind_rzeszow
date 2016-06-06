package com.miguelcr.a04_randomdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView diceImage;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImage = (ImageView) findViewById(R.id.imageViewDice);
        random = new Random();

    }

    public void doRandomDice(View view) {
        int max = 6;
        int min = 1;
        int randomResult = random.nextInt(max - min + 1) + min;

        int selectedImage = R.drawable.dice1;

        switch (randomResult) {
            case 1: selectedImage = R.drawable.dice1; break;
            case 2: selectedImage = R.drawable.dice2; break;
            case 3: selectedImage = R.drawable.dice3; break;
            case 4: selectedImage = R.drawable.dice4; break;
            case 5: selectedImage = R.drawable.dice5; break;
            case 6: selectedImage = R.drawable.dice6; break;
        }

        diceImage.setImageResource(selectedImage);
    }
}