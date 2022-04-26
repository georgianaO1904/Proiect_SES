package com.ehealth.application.appointeeth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.ehealth.application.appointeeth.HomePageActivity;
import com.ehealth.application.appointeeth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText emailID, password;
    Button loginButton;
    TextView signUp;
    ProgressBar progressBar;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // check if current user is not null
       if (firebaseUser != null){
            navigateToHomePage();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpRedirect);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
            if (mFirebaseUser != null) {
                navigateToHomePage();
            } else {
                Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
            }
        };

        loginButton.setOnClickListener(v -> {
            String email = emailID.getText().toString();
            String pwd = password.getText().toString();
            if (email.isEmpty()) {
                emailID.setError("Please enter email");
                emailID.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError("Please insert password");
                password.requestFocus();
            } else if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.setVisibility(View.VISIBLE);

                mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener <AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task < AuthResult > task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(getApplicationContext(), "Sucessful login!", Toast.LENGTH_LONG).show();
                            navigateToHomePage();
                        }
                    }
                });
            }
        });

        // signup button click listener
        signUp.setOnClickListener(v -> {
            BottomSheetDialog bottomSheet = new BottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "BottomSheet");
        });
    }

    private void navigateToHomePage() {
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

}