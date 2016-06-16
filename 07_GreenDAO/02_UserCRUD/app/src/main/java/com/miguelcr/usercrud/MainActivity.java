package com.miguelcr.usercrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcr.usercrud.greendao.DaoSession;
import com.miguelcr.usercrud.greendao.User;
import com.miguelcr.usercrud.greendao.UserDao;

public class MainActivity extends AppCompatActivity implements OnUserClickListener {
    UserDao userDao;
    User user;
    UserFragment userFragment;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment);
        userFragment = (UserFragment)f;

        // Database Connection
        DaoSession dbConnection = DatabaseConnection.getConnection(this);
        userDao = dbConnection.getUserDao();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the AddUserActivity
                Intent i = new Intent(MainActivity.this,NewUserActivity.class);
                i.putExtra("action","add");
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserClick(User u) {

    }

    @Override
    public void onUserDelete(User u) {
        user = u;

        // Show confirmation dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                userDao.delete(user);
                userFragment.refreshUserList();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.cancel();
            }
        });

        builder.setTitle("Delete User");
        builder.setMessage("Are you sure that you want to overclock the user?");

        // Create the AlertDialog
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onUserEdit(User u) {
        Intent i = new Intent(this, NewUserActivity.class);
        i.putExtra("action","edit");
        i.putExtra("idUser",u.getId());
        startActivity(i);
    }

}
