package com.ehealth.application.appointeeth.register;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ehealth.application.appointeeth.PacientHomePageActivity;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.DoctorHomePageActivity;
import com.ehealth.application.appointeeth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.ehealth.application.appointeeth.data.Constants.PACIENT_USER_TYPE;

public class RegisterPatientActivity extends AppCompatActivity {

    private EditText emailID, password, passwordValidation;
    private Button submitButton;
    private FirebaseAuth mFirebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pacient);

        // initializari
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        passwordValidation = findViewById(R.id.password_register_validate);
        submitButton = findViewById(R.id.signupbutton);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        // listener pentru butonul de register
        submitButton.setOnClickListener(view -> {

            final String email = emailID.getText().toString();
            final String psswd = password.getText().toString();
            final String psswdValidation = passwordValidation.getText().toString();

            if (email.isEmpty()) {
                emailID.setError("Please enter email");
                emailID.requestFocus();
            } else if (psswd.isEmpty()) {
                password.setError("Please insert password");
                password.requestFocus();
            } else if (!psswdValidation.equals(psswd)) {
                passwordValidation.setError("Please insert valid password");
                passwordValidation.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);

                // creare user nou (cu email) Firebase
                mFirebaseAuth.createUserWithEmailAndPassword(email, psswd).addOnCompleteListener(RegisterPatientActivity.this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast toast = Toast.makeText(getApplicationContext(), "You are already registered!", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        // userul este creat + autentificat cu Firebase => extragem Id-ul lui unic:
                        final String uid = mFirebaseAuth.getCurrentUser().getUid();

                        // userul exista in Firebase dar stocam datele suplimentare in Baza de date:
                        UserObject newUser = new UserObject(uid, email, PACIENT_USER_TYPE);
                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                        dbRef.child("users").child(uid).setValue(newUser);

                        // redirectare HomePage + afisare mesaj success
                        Toast.makeText(getApplicationContext(), "Sign up successful!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterPatientActivity.this, PacientHomePageActivity.class));
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}