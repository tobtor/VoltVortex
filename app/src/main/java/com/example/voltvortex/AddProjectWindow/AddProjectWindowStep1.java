package com.example.voltvortex.AddProjectWindow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.MainActivity;
import com.example.voltvortex.MyDatabaseHelper;
import com.example.voltvortex.ProjectModel;
import com.example.voltvortex.R;

public class AddProjectWindowStep1 extends AppCompatActivity {

    EditText projectName;
    Button buttonEndStep1, buttonAddToDataBase;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch swichIsManyCities, switchIsManyContactPerson;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window_step1);

        projectName = findViewById(R.id.editTextNazwaProjektu);
        buttonEndStep1 = findViewById(R.id.buttonEndStep1);
        buttonAddToDataBase = findViewById(R.id.buttonAddToDataBase);
        swichIsManyCities = findViewById(R.id.switchDodajProjektPytanie1);
        switchIsManyContactPerson = findViewById(R.id.switchDodajProjektPytanie2);

        buttonAddToDataBase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    ProjectModel projectModel = new ProjectModel(-1, projectName.getText().toString(),
                            swichIsManyCities.isChecked(), switchIsManyContactPerson.isChecked());
                    Toast.makeText(AddProjectWindowStep1.this, projectModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(AddProjectWindowStep1.this, "Błąd przy dodawaniu!", Toast.LENGTH_SHORT).show();
                }

//                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProjectWindowStep1.this);
//                    myDB.addProject(projectName.getText().toString().trim(),
//                            String.valueOf(swichIsManyCities.isChecked()).trim(),
//                            String.valueOf(switchIsManyContactPerson.isChecked()).trim());
            }
        });

            buttonEndStep1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    boolean isManyCities = swichIsManyCities.isChecked();
                    boolean isManyContactPerson = switchIsManyContactPerson.isChecked();

                    if(!isManyCities) {
                        Intent intent = new Intent(AddProjectWindowStep1.this, AddProjectWindowStep2.class);
                        startActivity(intent);
                    }
                    else if(!isManyContactPerson) {
                        Intent intent = new Intent(AddProjectWindowStep1.this, AddProjectWindowStep3.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(AddProjectWindowStep1.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            });

    }
}