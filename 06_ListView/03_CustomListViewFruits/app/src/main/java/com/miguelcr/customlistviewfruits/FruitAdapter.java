package com.miguelcr.customlistviewfruits;

import android.content.Context;
import android.media.Image;
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
public class FruitAdapter extends ArrayAdapter<Fruit>{
    Context ctx;
    List<Fruit> items;


    public FruitAdapter(Context ctx, List<Fruit> items) {
        super(ctx,R.layout.fruit_item,items);
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.fruit_item, parent, false);

        // 1. Get the layout components
        ImageView imageViewIcon = (ImageView)v.findViewById(R.id.iconFruit);
        TextView textViewName = (TextView)v.findViewById(R.id.nameFruit);
        TextView textViewPrice = (TextView)v.findViewById(R.id.priceFruit);

        // 2. Get the current item info
        Fruit current = items.get(position);
        String name = current.getName();
        int icon = current.getIcon();
        float price = current.getPrice();

        // 3. Set the info in the View Components
        textViewName.setText(name);
        textViewPrice.setText(String.valueOf(price));
        imageViewIcon.setImageResource(icon);

        return v;
    }
}
