package com.ehealth.application.appointeeth.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ehealth.application.appointeeth.DoctorHomePageActivity;
import com.ehealth.application.appointeeth.PacientHomePageActivity;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.ehealth.application.appointeeth.data.Constants.DOCTOR_USER_TYPE;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // check if current user is not null
        if (firebaseUser != null){
           startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (mFirebaseUser != null) {
                progressBar.setVisibility(View.GONE);
               navigateToHomePage();
            } else {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
        };

    }

    private void navigateToHomePage() {
        // extract current UserId
        String userId =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        // create the reference in the db to the current user
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        // read the current user object
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserObject user = dataSnapshot.getValue(UserObject.class);
                // check the current user's type
                if(user.getUserType().equals(DOCTOR_USER_TYPE)) {
                    startActivity(new Intent(SplashScreenActivity.this, DoctorHomePageActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, PacientHomePageActivity.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}