package com.miguelcr.navigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "DuckPrefFile";
    TextView logoText;
    Button btnLogin;
    SoundPool soundEffects;
    int gunSoundId;
    AudioAttributes aa;
    EditText nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoText = (TextView) findViewById(R.id.logoText);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        nickname = (EditText) findViewById(R.id.nickName);

        // Change the font type for the TextView logoText
        Typeface type = Typeface.createFromAsset(getAssets(),"pixel.otf");
        logoText.setTypeface(type);
        btnLogin.setTypeface(type);

        configSound();

    }

    public void doLogin(View view) {
        if(nickname.getText().toString().isEmpty()) {
            Toast.makeText(this,"Write a nickname!",Toast.LENGTH_LONG).show();
        } else {
            // Save the nickname for the future
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("nicknameUser", nickname.getText().toString());

            // Commit the edits!
            editor.commit();


            soundEffects.play(gunSoundId, 1, 1, 1, 1, 1);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
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
