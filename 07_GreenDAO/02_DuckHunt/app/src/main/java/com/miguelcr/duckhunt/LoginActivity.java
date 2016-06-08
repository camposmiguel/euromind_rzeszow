package com.miguelcr.duckhunt;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView logoText;
    Button btnLogin;
    SoundPool soundEffects;
    int gunSoundId;
    AudioAttributes aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoText = (TextView) findViewById(R.id.logoText);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Change the font type for the TextView logoText
        Typeface type = Typeface.createFromAsset(getAssets(),"pixel.otf");
        logoText.setTypeface(type);
        btnLogin.setTypeface(type);

        configSound();

    }

    public void doLogin(View view) {
        soundEffects.play(gunSoundId,1,1,1,1,1);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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
        gunSoundId = soundEffects.load(this,R.raw.gun,1);
    }
}
