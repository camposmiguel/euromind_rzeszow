package com.example.jorge.pato;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class PrincipalActivity extends AppCompatActivity {

    ImageView imagen,cerrar;
    int aleatorioX,AleatorioY;
    RelativeLayout layout;
    double largo,ancho;
    SoundPool soundPool;
    TextView puntos,tiempo,usuario;
    int sonidoFondo,sonidoMatar;
    int puntostotales=0;
    private AlertDialog.Builder builder;
    private AlertDialog.Builder builder1;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    CountDownTimer cuenta;
    boolean tiempores=true;
    MediaPlayer mediaPlayer;
    int timporespausa;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        checkInternet();
        layout=(RelativeLayout)findViewById(R.id.layout);
        imagen=(ImageView)findViewById(R.id.imageView);
        puntos=(TextView)findViewById(R.id.textViewPuntos);
        tiempo=(TextView)findViewById(R.id.textViewTiempo);
        usuario=(TextView)findViewById(R.id.textViewUser);
        cerrar=(ImageView)findViewById(R.id.imageViewCerrar);
        tiempo.setText("60 s");

        puntos.setText(String.valueOf(puntostotales));
        prefs =getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        usuario.setText(prefs.getString("nombre",null).toUpperCase());
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/pixel.TTF");
        usuario.setTypeface(custom_font);
        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();


        sonidoFondo = soundPool.load(this, R.raw.fondo, 1);



        sonidoMatar=soundPool.load(this,R.raw.cuak,1);



        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tiempores){
                    mediaPlayer = MediaPlayer.create(PrincipalActivity.this, R.raw.fondo);
                    mediaPlayer.start();
                    cuenta= new CountDownTimer(60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            tiempo.setText(millisUntilFinished / 1000 +" s");
                        }

                        public void onFinish() {
                            mediaPlayer.stop();
                            Intent i=new Intent(PrincipalActivity.this,RankingActivity.class);
                            editor=prefs.edit();
                            editor.putString("puntos", String.valueOf(puntostotales));
                            editor.commit();
                            startActivity(i);
                            finish();
                          /*  mediaPlayer.stop();
                            builder = new AlertDialog.Builder(PrincipalActivity.this);

                            builder.setTitle("Se acabo el tiempo");
                            builder.setMessage("El usuario " + prefs.getString("nombre", null) + " ha cazado " + puntos.getText().toString() + " patos");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(PrincipalActivity.this, PrincipalActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });


                            builder.create();
                            builder.show();*/
                        }
                    }.start();
                    tiempores=false;
                }


                Aleatorios();
                soundPool.play(sonidoMatar, 1, 1, 0, 0, 1);
                    puntostotales=puntostotales+1;
                    puntos.setText(String.valueOf(puntostotales));
                Random rnd = new Random();
                int pataso=rnd.nextInt(6)+1;
                if(pataso==2){
                    imagen.setBackgroundResource(R.drawable.pato2);
                }else if(pataso==3){
                    imagen.setBackgroundResource(R.drawable.pato3);
                }else if(pataso==4){
                    imagen.setBackgroundResource(R.drawable.pato4);
                }else if(pataso==5){
                    imagen.setBackgroundResource(R.drawable.pato5);
                }else if(pataso==6){
                    imagen.setBackgroundResource(R.drawable.pato6);
                }else{
                    imagen.setBackgroundResource(R.drawable.pato);
                }

            }
        });
    cerrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        timporespausa=Integer.valueOf(tiempo.getText().toString().replace(" s",""));
        if(cuenta!=null){
            cuenta.cancel();
        }
        builder1 = new AlertDialog.Builder(PrincipalActivity.this);

        builder1.setTitle("Seguro que desea salir");
        builder1.setCancelable(false);
        builder1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor=prefs.edit();
                editor.clear();
                editor.commit();
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }

                Intent i=new Intent(PrincipalActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                cuenta= new CountDownTimer(timporespausa*1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tiempo.setText(millisUntilFinished / 1000 +" s");
                    }

                    public void onFinish() {
                        mediaPlayer.stop();
                        Intent i=new Intent(PrincipalActivity.this,RankingActivity.class);
                        editor=prefs.edit();
                        editor.putString("puntos", String.valueOf(puntostotales));
                        editor.commit();
                        startActivity(i);
                        finish();
                    }
                }.start();
            }
        });
        builder1.create();
        builder1.show();
        }
    });
    }


    public void Aleatorios(){
        largo=layout.getWidth()-cerrar.getWidth()-imagen.getWidth();
        ancho=layout.getHeight()-cerrar.getHeight()-imagen.getHeight();
        aleatorioX=(int)Math.floor(Math.random()*largo);
        AleatorioY=(int)Math.floor(Math.random()*ancho);

        imagen.setX(aleatorioX);
        imagen.setY(AleatorioY);
    }
    private void checkInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle("No hay conexión...");
            builder.setMessage("Desea activar internet?");
            builder.setCancelable(false);
            builder.setPositiveButton("Si",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lanzo un intent implícito que hace que se abra
                            // la pantalla de Configuración del móvil
                            // para que el usuario se conecte a Internet
                            // Internet
                            Intent intent = new Intent(
                                    Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    });

            builder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();


                        }
                    });

            builder.create();
            builder.show();

        } else {


        }

    }
}
