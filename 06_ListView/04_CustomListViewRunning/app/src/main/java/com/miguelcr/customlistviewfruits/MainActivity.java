package com.miguelcr.customlistviewfruits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    List<Exercise> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. ListView
        lista = (ListView)findViewById(R.id.listViewFruits);

        // 2. List of Fruits elements
        items = new ArrayList<Exercise>();
        items.add(new Exercise(R.drawable.ic_run_fast,"Easy",15,500));
        items.add(new Exercise(R.drawable.ic_run_fast,"Medium",25,1500));
        items.add(new Exercise(R.drawable.ic_run_fast,"Advanced",40,5000));
        items.add(new Exercise(R.drawable.ic_run_fast,"Advanced",50,6000));

        // 3. Adapter
        ExerciseAdapter adapter = new ExerciseAdapter(this,items);

        // 4. Connect LisView AND Adapter
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Exercise current = items.get(position);

        Intent i = new Intent(this,DetailActivity.class);
        i.putExtra("title",current.getName());
        i.putExtra("duration",current.getDuration());
        i.putExtra("distance",current.getDistance());
        startActivity(i);
    }
}
