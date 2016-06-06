package com.miguelcr.customlistviewfruits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<Fruit> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. ListView
        lista = (ListView)findViewById(R.id.listViewFruits);

        // 2. List of Fruits elements
        items = new ArrayList<Fruit>();
        items.add(new Fruit(R.drawable.ic_strawberry,"Strawberry",2.99f));
        items.add(new Fruit(R.drawable.ic_banana,"Banana",1.29f));
        items.add(new Fruit(R.drawable.ic_tomato,"Tomato",0.99f));
        items.add(new Fruit(R.drawable.ic_watermelon,"Watermelon",1.49f));
        items.add(new Fruit(R.drawable.ic_apple,"Apple",1.59f));
        items.add(new Fruit(R.drawable.ic_pear,"Pear",1.79f));
        items.add(new Fruit(R.drawable.ic_cherries,"Cherry",2.79f));
        items.add(new Fruit(R.drawable.ic_orange,"Orange",1.39f));
        items.add(new Fruit(R.drawable.ic_pineapple,"Pineapple",1.69f));
        items.add(new Fruit(R.drawable.ic_chili,"Chili",0.89f));
        items.add(new Fruit(R.drawable.ic_raspberry,"Raspberry",2.10f));

        // 3. Adapter
        FruitAdapter adapter = new FruitAdapter(this,items);

        // 4. Connect LisView AND Adapter
        lista.setAdapter(adapter);

    }
}
