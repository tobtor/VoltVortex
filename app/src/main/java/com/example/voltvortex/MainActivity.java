package com.example.voltvortex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button addProjectButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch usuwanie;
    ListView listOfProjects;
    MyDatabaseHelper myDatabaseHelper;
    ArrayAdapter<ProjectModel> projectArrayAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProjectButton = findViewById(R.id.addProjectButton);
        listOfProjects = findViewById(R.id.listOfProjects);
        usuwanie = findViewById(R.id.usuwanie);

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);

        getProjectList(myDatabaseHelper);

        usuwanie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                listOfProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProjectModel clickedProject = (ProjectModel) parent.getItemAtPosition(position);
                        myDatabaseHelper.deleteProject(clickedProject);
                        getProjectList(myDatabaseHelper);
                        Toast.makeText(MainActivity.this, "Deleted " + clickedProject.toString() , Toast.LENGTH_SHORT).show();
                    }
                });} else {
                    ///
                }
            }
        });

        addProjectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectWindow.class);
                startActivity(intent);
            }
        });

    }

    private void getProjectList(MyDatabaseHelper myDatabaseHelper) {
        projectArrayAdapter = new ArrayAdapter<ProjectModel>
                (MainActivity.this, R.layout.activity_listview_layout, R.id.ListviewText, myDatabaseHelper.viewProjectList());
        listOfProjects.setAdapter(projectArrayAdapter);
    }

}