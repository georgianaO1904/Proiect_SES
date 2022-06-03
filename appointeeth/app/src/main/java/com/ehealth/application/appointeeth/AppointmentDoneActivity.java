package com.ehealth.application.appointeeth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AppointmentDoneActivity extends AppCompatActivity {
    String patientId;
    Button homePageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_done);

        homePageBtn = (Button) findViewById(R.id.home_button);

        homePageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PacientHomePageActivity.class);
            v.getContext().startActivity(intent);
        });

    }
}