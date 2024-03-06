package com.example.voltvortex.AddProjectWindow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.MainActivity;
import com.example.voltvortex.Project;
import com.example.voltvortex.R;

public class AddProjectWindowStep1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window_step1);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button addProjectButton = findViewById(R.id.buttonEndStep1);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichIsManyCities = findViewById(R.id.switchDodajProjektPytanie1);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchIsManyContactPerson = findViewById(R.id.switchDodajProjektPytanie2);

            addProjectButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    EditText editTextProjectName = findViewById(R.id.editTextNazwaProjektu);
                    String textProjectName = editTextProjectName.getText().toString();
                    boolean isManyCities = swichIsManyCities.isChecked();
                    boolean isManyContactPerson = switchIsManyContactPerson.isChecked();

                    Project project = new Project(textProjectName, isManyCities, isManyContactPerson);

                    if(!project.isManyCities()) {
                        Intent intent = new Intent(AddProjectWindowStep1.this, AddProjectWindowStep2.class);
                        startActivity(intent);
                    }
                    else if(!project.isManyContactPerson()) {
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