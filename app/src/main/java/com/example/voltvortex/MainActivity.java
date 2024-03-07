package com.example.voltvortex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.AddProjectWindow.AddProjectWindowStep1;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addProjectButton;
    ListView listOfProjects;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProjectButton = findViewById(R.id.addProjectButton);
        listOfProjects = findViewById(R.id.listOfProjects);

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        List<String> allInMemoryProjects = myDatabaseHelper.viewProjectList();

        ArrayAdapter projectArrayAdapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, allInMemoryProjects);

        listOfProjects.setAdapter(projectArrayAdapter);

        addProjectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectWindowStep1.class);
                startActivity(intent);
            }
        });

    }

}