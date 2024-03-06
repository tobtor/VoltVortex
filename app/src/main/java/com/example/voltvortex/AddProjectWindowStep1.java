package com.example.voltvortex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddProjectWindowStep1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window_step1);

        Button addProjectButton = findViewById(R.id.buttonZatwierdzProjekt);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichIsManyCities = findViewById(R.id.switchDodajProjektPytanie1);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchIsManyContactPerson = findViewById(R.id.switchDodajProjektPytanie2);

            addProjectButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    EditText editTextProjectName = findViewById(R.id.editTextNazwaProjektu);
                    String textProjectName = editTextProjectName.getText().toString();
                    boolean isSingleCity = swichIsManyCities.isChecked();
                    boolean isSingleContactPerson = switchIsManyContactPerson.isChecked();

                    Project project = new Project(textProjectName, isSingleCity, isSingleContactPerson);

                    if(project.isSingleCity()) {
                        Intent intent = new Intent(AddProjectWindowStep1.this, AddProjectWindowStep2.class);
                        startActivity(intent);
                    }
                }
            });

    }
}