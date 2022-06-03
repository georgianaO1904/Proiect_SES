package com.ehealth.application.appointeeth.appointment.view_appointment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ehealth.application.appointeeth.AppointmentDoneActivity;
import com.ehealth.application.appointeeth.DoctorHomePageActivity;
import com.ehealth.application.appointeeth.R;

public class AdapterDoctorViewAppointment extends AppCompatActivity {
    public Button homePageBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_appointments);

        homePageBtn = (Button) findViewById(R.id.home_button);

        homePageBtn.setOnClickListener(v ->{
            startActivity( new Intent(AdapterDoctorViewAppointment.this, DoctorHomePageActivity.class));
        });
    }
}
