package com.example.voltvortex.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Intefraces.ProjectRecyclerViewInterface;
import com.example.voltvortex.RecyclerViewAdapters.ProjectRecyclerViewAdapter;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProjectRecyclerViewInterface {

    Button addProjectButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch usuwanie;
    RecyclerView listOfProjects;
    MyDatabaseHelper myDatabaseHelper;
    ProjectRecyclerViewAdapter projectRecyclerViewAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Przypisanie widoków do zmiennych
        addProjectButton = findViewById(R.id.addProjectButton);
        listOfProjects = findViewById(R.id.listOfProjects);
        usuwanie = findViewById(R.id.usuwanie);

        // Inicjalizacja klasy pomocniczej bazy danych
        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);

        // Ustawienie LayoutManager dla RecyclerView
        listOfProjects.setLayoutManager(new LinearLayoutManager(this));

        // Wyświetlenie listy projektów
        getProjectList(myDatabaseHelper);

        // Listener zmiany stanu przełącznika usuwanie
        usuwanie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                projectRecyclerViewAdapter.setDeleteMode(isChecked);
            }
        });

        // Listener przycisku dodawania projektu
        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectWindow.class);
                startActivity(intent);
            }
        });
    }

    // Metoda do odświeżania listy projektów
    private void getProjectList(MyDatabaseHelper myDatabaseHelper) {
        List<ProjectModel> projectList = myDatabaseHelper.viewProjectList();
        projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(projectList, myDatabaseHelper, this);
        listOfProjects.setAdapter(projectRecyclerViewAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        ProjectModel projectModel = projectRecyclerViewAdapter.getProjectAt(position);
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("PROJECT_ID", projectModel.getProjectID());
        intent.putExtra("CONTACT_PERSON_ID", projectModel.getContactPersonID());
        startActivity(intent);
    }
}