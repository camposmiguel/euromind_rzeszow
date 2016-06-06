package com.miguelcr.customlistviewfruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miguelcampos on 6/6/16.
 */
public class ExerciseAdapter extends ArrayAdapter<Exercise>{
    Context ctx;
    List<Exercise> items;


    public ExerciseAdapter(Context ctx, List<Exercise> items) {
        super(ctx,R.layout.exercise_item,items);
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.exercise_item, parent, false);

        // 1. Get the layout components
        ImageView imageViewIcon = (ImageView)v.findViewById(R.id.iconExercise);
        TextView textViewName = (TextView)v.findViewById(R.id.nameExercise);
        TextView textViewDuration = (TextView)v.findViewById(R.id.durationExercise);
        TextView textViewDistance = (TextView)v.findViewById(R.id.distanceExercise);

        // 2. Get the current item info
        Exercise current = items.get(position);
        String name = current.getName();
        int icon = current.getIcon();
        int duration = current.getDuration();
        int distance = current.getDistance();

        // 3. Set the info in the View Components
        textViewName.setText(name);
        textViewDuration.setText(String.valueOf(duration)+"'");
        textViewDistance.setText(String.valueOf(distance/1000)+" Km");
        imageViewIcon.setImageResource(icon);

        return v;
    }
}
