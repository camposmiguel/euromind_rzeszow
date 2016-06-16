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
    long idUser;
    UserDao userDao;
    User u;
    RadioButton rbMale, rbFemale;
    String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Bundle extras = getIntent().getExtras();
        action = extras.getString("action");

        etName = (EditText)findViewById(R.id.editTextName);
        etAge = (EditText)findViewById(R.id.editTextAge);
        etLocation = (EditText)findViewById(R.id.editTextLocation);
        rgSex = (RadioGroup) findViewById(R.id.radioSex);
        rbMale = (RadioButton) findViewById(R.id.sexMale);
        rbFemale = (RadioButton) findViewById(R.id.sexFemale);

        if(action!=null && action.equals("edit")) {
            idUser = extras.getLong("idUser");
            DaoSession dbSession = DatabaseConnection.getConnection(this);
            userDao = dbSession.getUserDao();
            u = userDao.load(idUser);

            etName.setText(u.getName());
            etAge.setText(String.valueOf(u.getAge()));
            etLocation.setText(String.valueOf(u.getLocation()));
            if(u.getSex().equals("m")) {
                rbMale.setChecked(true);
            } else {
                rbFemale.setChecked(true);
            }

        }
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
        newUser.setSex(sex);
        if(action.equals("edit")) {
            newUser.setId(u.getId());
            userDao.update(newUser);
        } else {
            userDao.insert(newUser);
        }

        // close the Activity
        finish();

    }
}
