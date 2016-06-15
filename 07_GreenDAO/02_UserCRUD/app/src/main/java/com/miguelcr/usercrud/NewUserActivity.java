package com.miguelcr.usercrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.miguelcr.usercrud.greendao.DaoSession;
import com.miguelcr.usercrud.greendao.User;
import com.miguelcr.usercrud.greendao.UserDao;

public class NewUserActivity extends AppCompatActivity {
    EditText etName, etAge, etLocation;
    RadioGroup rgSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        etName = (EditText)findViewById(R.id.editTextName);
        etAge = (EditText)findViewById(R.id.editTextAge);
        etLocation = (EditText)findViewById(R.id.editTextLocation);
        rgSex = (RadioGroup) findViewById(R.id.radioSex);
    }

    public void createUser(View view) {
        DaoSession dbSession = DatabaseConnection.getConnection(this);
        UserDao userDao = dbSession.getUserDao();

        // Get the user info
        String name = etName.getText().toString();
        int age = Integer.valueOf(etAge.getText().toString());
        String location = etLocation.getText().toString();
        // get Sex
        int idOfRadioButtonSelected = rgSex.getCheckedRadioButtonId();
        String sex = "f";
        if(idOfRadioButtonSelected==R.id.sexMale) {
          sex = "m";
        }

        // Create User in Database
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setLocation(location);
        userDao.insert(newUser);

        // close the Activity
        finish();

    }
}
