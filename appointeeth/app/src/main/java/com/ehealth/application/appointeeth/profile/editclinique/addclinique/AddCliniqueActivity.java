package com.ehealth.application.appointeeth.profile.editclinique.addclinique;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.ehealth.application.appointeeth.DoctorHomePageActivity;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.register.RegisterDoctorActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCliniqueActivity extends AppCompatActivity {

    private EditText cliniqueName, cliniqueLocation;
    private Button submitButton;
    private ProgressBar progressBar;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinique);

        cliniqueName = findViewById(R.id.add_clinique_name);
        cliniqueLocation = findViewById(R.id.add_clinique_location);
        submitButton = findViewById(R.id.submit_clinique_button);
        progressBar = findViewById(R.id.progressbar);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        submitButton.setOnClickListener(view -> {

            final String location = cliniqueLocation.getText().toString();
            final String name = cliniqueName.getText().toString();

            if (name.isEmpty()) {
                cliniqueName.setError("Please enter clinique name");
                cliniqueName.requestFocus();
            } else if (location.isEmpty()) {
                cliniqueLocation.setError("Please enter clinique location");
                cliniqueLocation.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

                // generare id nou pt obiectul clinica
                String id = dbRef.child("users").child(userId).child("cliniques").push().getKey();
                CliniqueObject newClinique = new CliniqueObject(id, name, location);

                dbRef.child("users").child(userId).child("cliniques").child(id).setValue(newClinique).addOnCompleteListener( task -> {
                    progressBar.setVisibility(View.INVISIBLE);

                        if(task.isSuccessful()){
                          Toast.makeText(getApplicationContext(), "Clinique added successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "There was an error, please try again later!", Toast.LENGTH_LONG).show();
                        }
                    }
                );
            }
        });
    }
}