package com.miguelcr.activityintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.editTextEmail);
        pass = (EditText) findViewById(R.id.editTextPassword);
        terms = (CheckBox) findViewById(R.id.terms);

    }

    public void doLogin(View view) {
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();
        boolean userTerms = terms.isChecked();

        if(userEmail.equals("admin@admin.com") && userPass.equals("1234")) {
            if(userTerms) {
                // Login ok and we can launch de UserActivity
                Intent i = new Intent(MainActivity.this,UserActivity.class);
                startActivity(i);
            } else {
                // The user didn't accept the terms
                Toast.makeText(this,"Yo must to accept the terms and conditions",Toast.LENGTH_LONG).show();
            }
        } else {
            // The user or pass are not ok
            email.setError("Email incorrect");
            pass.setError("Password incorrect");
            // Clear the fields
            email.getText().clear();
            pass.getText().clear();
        }
    }
}
