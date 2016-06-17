package com.miguelcr.duckhunt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView duck;
    Random random;
    int counter = 0;
    TextView tvCounterDucks, nick, tvTimer;
    SoundPool soundEffects;
    int duckSoundId;
    AudioAttributes aa;
    AlertDialog.Builder builder;

    JSONArray response = new JSONArray();
    ProgressDialog progressDialog;
    PojoUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        duck = (ImageView)findViewById(R.id.duckImage);
        tvCounterDucks = (TextView) findViewById(R.id.countDucks);
        nick = (TextView) findViewById(R.id.nickName);
        tvTimer = (TextView) findViewById(R.id.countDownTimer);

        // Get the value of nickname
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String userNick = settings.getString("nicknameUser", "");
        nick.setText(userNick);

        // Change the font type for the TextView tvCounterDucks
        Typeface type = Typeface.createFromAsset(getAssets(),"pixel.otf");
        tvCounterDucks.setTypeface(type);
        nick.setTypeface(type);
        tvTimer.setTypeface(type);

        configSound();

        randomDuckPosition();

        startCountDown();

        alertGameOver();
    }

    private void alertGameOver() {
        builder = new AlertDialog.Builder(this);
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
                finish();
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

        soundEffects.play(duckSoundId,1,1,1,1,1);

    }

    public void duckClicked(View view) {
        counter++;
        tvCounterDucks.setText("Ducks: "+String.valueOf(counter));
        randomDuckPosition();
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
        duckSoundId = soundEffects.load(this,R.raw.cuak,1);
    }

    private class MyRankingTask extends AsyncTask<Void, Void, Void> {
        URL url = null;
        HttpURLConnection urlConnection = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {

                url=new URL("http://rest.miguelcr.com/killduck/user?nickname="+prefs.getString("nombre",null)+"&points="+prefs.getString("puntos",null));
                urlConnection = (HttpURLConnection) url.openConnection();
                String responseString = readStream(urlConnection.getInputStream());
                response = new JSONArray(responseString);
                Log.e("tamanio",String.valueOf(response.length()));

                String userId=response.getJSONObject(0).getString("id");
                String userRegis=response.getJSONObject(0).getString("nickname");
                String userPoint=response.getJSONObject(0).getString("points");
                Log.e("usuarios",userRegis);
                currentUser = new PojoUser(userId,userRegis,userPoint);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Cargando ranking...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new UsuariosRankingTask().execute();
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
