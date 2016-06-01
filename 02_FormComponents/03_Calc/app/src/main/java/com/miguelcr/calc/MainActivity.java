package com.miguelcr.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b7, b8, b9, btnDel, btnClear, btnEq, btnAdd;
    TextView screen;
    int op1 = 0, op2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b7 = (Button)findViewById(R.id.btn7);
        b8 = (Button)findViewById(R.id.btn8);
        b9 = (Button)findViewById(R.id.btn9);
        btnDel = (Button)findViewById(R.id.btnDel);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnEq = (Button)findViewById(R.id.btnEq);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        screen = (TextView) findViewById(R.id.screen);

        // Define the click event listener for ALL BUTTONS
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnEq.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button btnClicked = (Button)v;

        int id = btnClicked.getId();

        String screenText = screen.getText().toString();

        int len = screenText.length();
        String newText = screenText;
        if(screenText.equals("0")) {
            newText = "";
        }

        switch (id) {
            case R.id.btn7: screen.setText(newText+"7"); break;
            case R.id.btn8: screen.setText(newText+"8"); break;
            case R.id.btn9: screen.setText(newText+"9"); break;
            case R.id.btnDel: screen.setText(screenText.substring(0,len-1)); break;
            case R.id.btnClear:
                screen.setText("");
                op1 = 0;
                op2 = 0;
                break;
            case R.id.btnAdd:
                screen.setText("0");
                op1 = Integer.valueOf(screenText).intValue();
                break;
            case R.id.btnEq:
                op2 = Integer.valueOf(screenText).intValue();
                int addResult = op1+op2;
                screen.setText(String.valueOf(addResult));
        }

    }
}
