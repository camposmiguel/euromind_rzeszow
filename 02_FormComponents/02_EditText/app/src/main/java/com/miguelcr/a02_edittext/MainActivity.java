package com.miguelcr.a02_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the 'email' var with the
        // component with the id 'editTextEmail'
        email = (EditText) findViewById(R.id.editTextEmail);

        // Connect the 'pass' var with the
        // component with the id 'editTextPassword'
        pass = (EditText) findViewById(R.id.editTextPassword);


    }

    public void doLogin(View view) {
        // Now we get the text from email and pass
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        // if userEmail is 'admin@admin.com'
        // and userPass is '1234' LOGIN OK!
        if(userEmail.equals("admin@admin.com") && userPass.equals("1234")) {
            Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
            email.getText().clear();
            pass.getText().clear();

        } else {
            Toast.makeText(MainActivity.this, "Login WRONG", Toast.LENGTH_SHORT).show();
        }
    }
}
