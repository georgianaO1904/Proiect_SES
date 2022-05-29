package com.ehealth.application.appointeeth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ehealth.application.appointeeth.login.LoginActivity;
import com.ehealth.application.appointeeth.profile.DoctorProfileActivity;
import com.ehealth.application.appointeeth.profile.PatientProfileActivity;
import com.ehealth.application.appointeeth.profile.searchservices.PatientSearchServicesActivity;
import com.google.firebase.auth.FirebaseAuth;

public class PacientHomePageActivity extends AppCompatActivity {

    private Button logoutButton, profileButton, searchServiceButton;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

        // Initializari
        mFirebaseAuth = FirebaseAuth.getInstance();
        profileButton = findViewById(R.id.ProfileButton);

        logoutButton = findViewById(R.id.LogoutButton);

        logoutButton.setOnClickListener(v -> {
            mFirebaseAuth.signOut();
            startActivity(new Intent(PacientHomePageActivity.this, LoginActivity.class));
        });

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(PacientHomePageActivity.this, PatientProfileActivity.class));
        });

        searchServiceButton = findViewById(R.id.searchServiceButton);
        searchServiceButton.setOnClickListener(view -> {
            startActivity(new Intent(PacientHomePageActivity.this, PatientSearchServicesActivity.class));
        });
    }
}