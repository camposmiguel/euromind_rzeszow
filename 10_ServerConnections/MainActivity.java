package com.example.jorge.pato;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    EditText usuario;
    Button entrar;
    String userregis;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        String url="http://rest.miguelcr.com/killduck/register?nickname=XXXX";

        entrar=(Button)findViewById(R.id.buttonLogin);
        prefs =getSharedPreferences("Usuario", Context.MODE_PRIVATE);


        if(prefs.getString("nombre",null)!=null){
            Intent i=new Intent(MainActivity.this,PrincipalActivity.class);
            startActivity(i);
            finish();
        }

        usuario=(EditText)findViewById(R.id.editTextUsuario);
        entrar.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          checkInternet();


                                      }
                                  });

    }
    private class RegistroTask extends AsyncTask<Void, Void, Void> {


        URL url = null;
        HttpURLConnection urlConnection = null;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                url=new URL("http://rest.miguelcr.com/killduck/register?nickname="+ userregis);
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
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Iniciando Sesion...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Intent i = new Intent(MainActivity.this, PrincipalActivity.class);
            startActivity(i);
            finish();
        }
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
            if(usuario.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Completa el campo usuario", Toast.LENGTH_SHORT).show();
            }else {
                userregis=usuario.getText().toString();


                editor = prefs.edit();
                editor.putString("nombre", usuario.getText().toString());
                editor.commit();
                new RegistroTask().execute();

            }

        }

    }
}
