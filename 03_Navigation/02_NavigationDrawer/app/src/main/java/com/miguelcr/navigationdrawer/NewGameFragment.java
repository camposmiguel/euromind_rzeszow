package com.miguelcr.navigationdrawer;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewGameFragment extends Fragment {
    ImageView duck;
    Random random;
    int counter = 0;
    TextView tvCounterDucks, nick, tvTimer;
    SoundPool soundEffects;
    int duckSoundId;
    AudioAttributes aa;
    AlertDialog.Builder builder;

    public NewGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_game, container, false);

        duck = (ImageView) v.findViewById(R.id.duckImage);
        tvCounterDucks = (TextView) v.findViewById(R.id.countDucks);
        nick = (TextView) v.findViewById(R.id.nickName);
        tvTimer = (TextView) v.findViewById(R.id.countDownTimer);

        // Get the value of nickname
        SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String userNick = settings.getString("nicknameUser", "");
        nick.setText(userNick);

        // Change the font type for the TextView tvCounterDucks
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"pixel.otf");
        tvCounterDucks.setTypeface(type);
        nick.setTypeface(type);
        tvTimer.setTypeface(type);

        configSound();

        randomDuckPosition();

        startCountDown();

        alertGameOver();

        duck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvCounterDucks.setText("Ducks: "+String.valueOf(counter));
                randomDuckPosition();
            }
        });


        return v;
    }

    private void alertGameOver() {
        builder = new AlertDialog.Builder(getActivity());
        // Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                counter = 0;
                startCountDown();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                getActivity().finish();
            }
        });
        // Set other dialog properties


        // Create the AlertDialog
        AlertDialog dialog = builder.create();
    }

    private void startCountDown() {
        //1 minute = 60000 milliseconds
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tvTimer.setText("Game over!");
                builder.setTitle("Game Over! Yo have hunted "+counter+" ducks. " +
                        "Do you want to play again?");
                builder.show();
            }
        }.start();

    }

    private void randomDuckPosition() {
        // Screen Size
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        random = new Random();
        int randomX = random.nextInt(width-duck.getWidth() + 1);
        int randomY = random.nextInt(height-duck.getHeight() + 1);
        duck.setX(randomX);
        duck.setY(randomY);

        soundEffects.play(duckSoundId,1,1,1,1,1);

    }


    public void configSound() {

        // Audio properties
        aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundEffects = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();

        // Load sound of duck
        duckSoundId = soundEffects.load(getActivity(),R.raw.cuak,1);
    }
}