package com.miguelcr.customlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    List<Student> lista;
    int layoutItem;


    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.layoutItem = resource;
        this.context = context;
        this.lista = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(layoutItem, parent, false);

        // Get the student info
        Student current = lista.get(position);
        String name = current.getName();
        int age = current.getAge();
        char sex = current.getSex();

        // Get the components from the layout
        ImageView avatar = (ImageView) v.findViewById(R.id.imageViewAvatar);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        TextView textViewAge = (TextView) v.findViewById(R.id.textViewAgeStudent);

        // Set the data to the components
        textViewName.setText(name);
        textViewAge.setText(String.valueOf(age));

        if(sex=='m') {
            avatar.setImageResource(android.R.drawable.btn_star_big_off);
        } else {
            avatar.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return v;
    }
}
