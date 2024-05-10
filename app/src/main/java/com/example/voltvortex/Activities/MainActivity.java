package com.example.voltvortex.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;

public class MainActivity extends AppCompatActivity {

    Button addProjectButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch usuwanie;
    ListView listOfProjects;
    MyDatabaseHelper myDatabaseHelper;
    ArrayAdapter<ProjectModel> projectArrayAdapter;
    TextView textViewName, textViewId;
    ProjectModel projectModel;

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
                (MainActivity.this, R.layout.activity_listview_layout, R.id.listViewTextProjectName, myDatabaseHelper.viewProjectList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                textViewName = view.findViewById(R.id.listViewTextProjectName);
                textViewId = view.findViewById(R.id.textAddingDate);

                projectModel = getItem(position);

                if (projectModel != null) {
                    textViewName.setText(projectModel.getProjectName());
                    textViewId.setText(String.valueOf(projectModel.getProjectID()));
                }

                return view;
            }
        };

        listOfProjects.setAdapter(projectArrayAdapter);
    }

}