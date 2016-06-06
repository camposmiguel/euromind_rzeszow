package com.miguelcr.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<Student> studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView
        lista = (ListView) findViewById(R.id.listViewStudents);

        // Items to show
        studentsList = new ArrayList<Student>();
        studentsList.add(new Student("John",23,'m'));
        studentsList.add(new Student("Mary",18,'f'));
        studentsList.add(new Student("Anthony",21,'m'));

        // Adapter
        StudentAdapter studentAdapter = new StudentAdapter(this,
                R.layout.student_item,studentsList);

        lista.setAdapter(studentAdapter);


    }
}
