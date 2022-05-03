package com.ehealth.application.appointeeth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ehealth.application.appointeeth.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class PacientHomePageActivity extends AppCompatActivity {

    private Button logoutButton;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

        // Initializari
        mFirebaseAuth = FirebaseAuth.getInstance();

        logoutButton = findViewById(R.id.LogoutButton);
        logoutButton.setOnClickListener(v -> {
            mFirebaseAuth.signOut();
            startActivity(new Intent(PacientHomePageActivity.this, LoginActivity.class));
        });
    }
}