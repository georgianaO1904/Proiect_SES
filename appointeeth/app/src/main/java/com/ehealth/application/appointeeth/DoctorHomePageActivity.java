package com.ehealth.application.appointeeth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ehealth.application.appointeeth.appointment.view_appointment.DoctorViewAppointmentActivity;
import com.ehealth.application.appointeeth.appointment.view_appointment.DoctorViewAppointmentAdapter;
import com.ehealth.application.appointeeth.login.LoginActivity;
import com.ehealth.application.appointeeth.profile.DoctorProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorHomePageActivity extends AppCompatActivity {

    private Button logoutButton, profileButton, appointmentButton;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        logoutButton = findViewById(R.id.LogoutButton);
        profileButton = findViewById(R.id.ProfileButton);
        appointmentButton = findViewById(R.id.AppointmentButton);

        mFirebaseAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(v -> {
            mFirebaseAuth.signOut();
            startActivity(new Intent(DoctorHomePageActivity.this, LoginActivity.class));
        });

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(DoctorHomePageActivity.this, DoctorProfileActivity.class));
        });

        appointmentButton.setOnClickListener(v ->{
            startActivity(new Intent(DoctorHomePageActivity.this, DoctorViewAppointmentActivity.class));
        });
    }
}