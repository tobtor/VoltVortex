package com.example.voltvortex;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddProjectWindowStep1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window_step1);

        Button addProjectButton = findViewById(R.id.buttonZatwierdzProjekt);

        Switch swichIsManyCities = findViewById(R.id.switchDodajProjektPytanie1);

        Switch switchIsManyContactPerson = findViewById(R.id.switchDodajProjektPytanie2);

        Boolean isSingleCity = swichIsManyCities.isChecked();
        Boolean isSingleContactPerson = switchIsManyContactPerson.isChecked();

        if(isSingleCity) {
            addProjectButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(AddProjectWindowStep1.this, AddProjectWindowStep2.class);
                    startActivity(intent);
                }
            });
        }
    }
}