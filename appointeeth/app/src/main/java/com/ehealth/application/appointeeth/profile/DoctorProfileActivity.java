package com.ehealth.application.appointeeth.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editservices.ServiceBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editworkhours.SelectCliniqueActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorProfileActivity extends AppCompatActivity {

    TextView servicesEditView, workhoursEditView, cliniquesEditView;
    private String uid;
    private FirebaseAuth mFirebaseAuth;

    private TextView doctorName, doctorEmail;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        servicesEditView = findViewById(R.id.services_edit_view);
        cliniquesEditView = findViewById(R.id.cliniques_edit_view);
        workhoursEditView = findViewById(R.id.workhours_edit_view);

        servicesEditView.setOnClickListener(view -> {
            ServiceBottomSheetDialog bottomSheet = new ServiceBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "ServicesBottomSheet");
        });

        doctorName = (TextView) findViewById(R.id.fullname);
        doctorEmail = (TextView) findViewById(R.id.email);

        mFirebaseAuth = FirebaseAuth.getInstance();
        uid = mFirebaseAuth.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("users").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserObject user = (UserObject) snapshot.getValue(UserObject.class);
                doctorName.setText(user.getUserName());
                doctorEmail.setText(user.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        workhoursEditView.setOnClickListener(view -> {
           startActivity(new Intent(DoctorProfileActivity.this, SelectCliniqueActivity.class));
        });

        cliniquesEditView.setOnClickListener(view -> {
            CliniqueBottomSheetDialog bottomSheet = new CliniqueBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "CliniqueBottomSheet");
        });

    }
}