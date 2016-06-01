package com.miguelcr.simplecomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int counter1 = 0, counter2 = 0;
    TextView tvCounter1, tvCounter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter1 = (TextView)findViewById(R.id.textViewCounter1);
        tvCounter2 = (TextView)findViewById(R.id.textViewCounter2);
    }

    public void showMessage(View view) {
        Button btnClicked = (Button)view;
        int idButton = btnClicked.getId();

        if(idButton==R.id.button){
            counter1++;
            tvCounter1.setText("Counter 1: "+counter1);
            Toast.makeText(MainActivity.this,"Button clicked 1: "+counter1,Toast.LENGTH_LONG).show();
        } else {
            counter2++;
            tvCounter2.setText("Counter 2: "+counter2);
            Toast.makeText(MainActivity.this,"Button clicked 2: "+counter2,Toast.LENGTH_LONG).show();
        }

    }
}
