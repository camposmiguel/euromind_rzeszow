package com.miguelcr.duckhunt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "DuckPrefFile";
    TextView logoText;
    Button btnLogin;
    SoundPool soundEffects;
    int gunSoundId;
    AudioAttributes aa;
    EditText nickname;

    ProgressDialog progressDialog;

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

            new RegisterTask().execute(nickname.getText().toString());

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

    private class RegisterTask extends AsyncTask<String, Void, Void> {


        URL url = null;
        HttpURLConnection urlConnection = null;

        @Override
        protected Void doInBackground(String... params) {
            try {
                url=new URL("http://rest.miguelcr.com/killduck/register?nickname="+ params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));

                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Wait, ducks are eating...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}
