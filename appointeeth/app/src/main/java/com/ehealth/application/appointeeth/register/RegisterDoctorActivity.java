package com.ehealth.application.appointeeth.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.DoctorHomePageActivity;
import com.ehealth.application.appointeeth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.ehealth.application.appointeeth.data.Constants.DOCTOR_USER_TYPE;

public class RegisterDoctorActivity extends AppCompatActivity  {

    private EditText name, emailID, password, passwordValidation, cuim;
    private Button submitButton;
    private FirebaseAuth mFirebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);

        // initializari
        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        emailID = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        passwordValidation = findViewById(R.id.password_register_validate);
        cuim = findViewById(R.id.cuim);
        submitButton = findViewById(R.id.signupbutton);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        // listener pentru butonul de register
        submitButton.setOnClickListener(view -> {

            final String nme = name.getText().toString();
            final String email = emailID.getText().toString();
            final String cuimIdentifier = cuim.getText().toString();
            final String psswd = password.getText().toString();
            final String psswdValidation = passwordValidation.getText().toString();

            if (nme.isEmpty()) {
                name.setError("Please enter email");
                name.requestFocus();
            } else if (email.isEmpty()) {
                emailID.setError("Please enter email");
                emailID.requestFocus();
            } else if (psswd.isEmpty()) {
                password.setError("Please insert password");
                password.requestFocus();
            } else if (!psswdValidation.equals(psswd)) {
                passwordValidation.setError("Please insert valid password");
                passwordValidation.requestFocus();
            }else if (cuimIdentifier.length() != 10) {
                cuim.setError("Please insert valid cuim identifier");
                cuim.requestFocus();
            } else {
               progressBar.setVisibility(View.VISIBLE);

                // creare user nou (cu email) Firebase
                mFirebaseAuth.createUserWithEmailAndPassword(email, psswd).addOnCompleteListener(RegisterDoctorActivity.this, task -> {
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
                        UserObject newUser = new UserObject(uid, nme, email, DOCTOR_USER_TYPE, cuimIdentifier);
                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                        dbRef.child("users").child(uid).setValue(newUser);

                        // redirectare HomePage + afisare mesaj success
                        Toast.makeText(getApplicationContext(), "Sign up successful!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterDoctorActivity.this, DoctorHomePageActivity.class));
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