package com.ehealth.application.appointeeth.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfileActivity extends AppCompatActivity {

    private String uid;
    private FirebaseAuth mFirebaseAuth;

    private TextView pacientName, pacientEmail;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        pacientName = (TextView) findViewById(R.id.fullname);
        pacientEmail = (TextView) findViewById(R.id.email);

        mFirebaseAuth = FirebaseAuth.getInstance();
        uid = mFirebaseAuth.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("users").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserObject user = (UserObject) snapshot.getValue(UserObject.class);
                pacientName.setText(user.getUserName());
                pacientEmail.setText(user.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}