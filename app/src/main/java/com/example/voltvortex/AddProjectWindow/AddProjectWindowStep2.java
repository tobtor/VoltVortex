package com.example.voltvortex.AddProjectWindow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.MainActivity;
import com.example.voltvortex.Project;
import com.example.voltvortex.R;

public class AddProjectWindowStep2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window_step2);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button addProjectButton = findViewById(R.id.buttonEndStep2);

        addProjectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(!project.isManyContactPerson()) {
                    Intent intent = new Intent(AddProjectWindowStep2.this, AddProjectWindowStep3.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(AddProjectWindowStep2.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}