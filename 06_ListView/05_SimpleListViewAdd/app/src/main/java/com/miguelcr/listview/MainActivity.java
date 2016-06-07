package com.miguelcr.listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    EditText newTeam;
    List<String> footballTeams;
    int[] rotations;
    ArrayAdapter<String> teamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = (ListView) findViewById(R.id.listViewTeams);
        newTeam = (EditText) findViewById(R.id.editTextNewTeam);

        footballTeams = new ArrayList<>();
        footballTeams.add("1. Real Madrid");

        rotations = new int[]{0,0,0,0,0};

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameOfNewTeam = newTeam.getText().toString();

                if(nameOfNewTeam.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please, write something!",Toast.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "New football team added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    int index = footballTeams.size() + 1;
                    footballTeams.add(index + ". " + nameOfNewTeam);
                    teamAdapter.notifyDataSetChanged();

                    // Clear the content of EditText, after we add the item
                    newTeam.getText().clear();

                }

            }
        });

        teamAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, footballTeams);

        lista.setAdapter(teamAdapter);
        lista.setOnItemClickListener(this);

        // Connect the listView with the Context Menu
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_context_delete:
                footballTeams.remove(info.position);
                teamAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Team: "+footballTeams.get(position),Toast.LENGTH_LONG).show();

        if(rotations[position]==0) {
            view.animate().rotationX(360).setDuration(500).alpha(0.1f).start();
            rotations[position]=360;
        } else {
            view.animate().rotationX(0).setDuration(500).alpha(1.0f).start();
            rotations[position]=0;
        }

    }
}
