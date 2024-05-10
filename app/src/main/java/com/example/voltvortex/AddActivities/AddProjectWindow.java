package com.example.voltvortex.AddActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Activities.MainActivity;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;

public class AddProjectWindow extends AppCompatActivity {

    EditText projectName, firm, description, contactPersonName, contactPersonPhoneNumber;
    Button buttonEndAddingProject;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchIsSingleContactPerson;
    LinearLayout LinearLayoutProjectContactPerson;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window);

        projectName = findViewById(R.id.editTextProjectName);
        firm = findViewById(R.id.editTextFirm);
        description = findViewById(R.id.editTextDescription);
        contactPersonName = findViewById(R.id.editTextOsobaKontaktowa);
        contactPersonPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonEndAddingProject = findViewById(R.id.buttonEndAddingProject);
        switchIsSingleContactPerson = findViewById(R.id.switchIsSingleContactPerson);
        LinearLayoutProjectContactPerson = findViewById(R.id.LinearLayoutProjectContactPerson);

        switchIsSingleContactPerson.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LinearLayoutProjectContactPerson.setVisibility(View.GONE);
                } else {
                    LinearLayoutProjectContactPerson.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonEndAddingProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ProjectModel projectModel;

                try {
                    projectModel = new ProjectModel(-1, projectName.getText().toString(), firm.getText().toString(),
                            description.getText().toString(), switchIsSingleContactPerson.isChecked());
                        Toast.makeText(AddProjectWindow.this, projectModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(AddProjectWindow.this, "Błąd przy dodawaniu!", Toast.LENGTH_SHORT).show();
                        projectModel = new ProjectModel(-1, "error","error","error",
                                switchIsSingleContactPerson.isChecked());
                    }

                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddProjectWindow.this);

                    boolean success = myDatabaseHelper.addProject(projectModel);

                    Toast.makeText(AddProjectWindow.this, "Success " + success, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddProjectWindow.this, MainActivity.class);
                    startActivity(intent);

                }
            });
    }
}