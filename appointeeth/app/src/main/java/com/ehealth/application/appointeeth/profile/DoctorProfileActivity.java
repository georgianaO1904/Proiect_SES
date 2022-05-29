package com.ehealth.application.appointeeth.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editservices.ServiceBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editworkhours.SelectCliniqueActivity;
import com.ehealth.application.appointeeth.profile.listcliniques.CliniqueProfileListAdapter;
import com.ehealth.application.appointeeth.profile.listservices.ServiceProfileListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class DoctorProfileActivity extends AppCompatActivity {

    TextView servicesEditView, workhoursEditView, cliniquesEditView;
    private String uid;
    private FirebaseAuth mFirebaseAuth;

    private TextView doctorName, doctorEmail;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    RecyclerView servicesRecyclerView, cliniquesRecyclerView;

    DatabaseReference dbRefCliniques, dbRefServices;
    String userId;

    ServiceProfileListAdapter servicesAdapter;
    ArrayList<ServiceObject> serviceList;

    CliniqueProfileListAdapter cliniquesAdapter;
    ArrayList<CliniqueObject> cliniqueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        servicesRecyclerView = findViewById(R.id.services_recyclerview);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cliniquesRecyclerView = findViewById(R.id.cliniques_recyclerview);
        cliniquesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRefCliniques = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("cliniques");
        dbRefServices = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("services");

        dbRefCliniques.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cliniqueList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        cliniqueList.add(ds.getValue(CliniqueObject.class));
                    }
                }

                cliniquesAdapter = new CliniqueProfileListAdapter(cliniqueList);
                cliniquesRecyclerView.setAdapter(cliniquesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        dbRefServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    serviceList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        serviceList.add(ds.getValue(ServiceObject.class));
                    }
                }

                servicesAdapter = new ServiceProfileListAdapter(serviceList);
                servicesRecyclerView.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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