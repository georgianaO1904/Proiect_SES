package com.ehealth.application.appointeeth.profile.searchservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.DoctorProfileActivity;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueListAdapter;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.RemoveCliniqueActivity;
import com.ehealth.application.appointeeth.profile.editservices.addservice.AddServiceActivity;
import com.ehealth.application.appointeeth.profile.listcliniques.CliniqueProfileListAdapter;
import com.ehealth.application.appointeeth.profile.listservices.ServiceProfileListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientSearchServicesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PatientSearchServicesListAdapter adapter;
    ArrayList<ServiceObject> servicesList;
    DatabaseReference dbRef;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_services);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbRef = FirebaseDatabase.getInstance().getReference().child("services");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    servicesList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        servicesList.add(ds.getValue(ServiceObject.class));
                    }
                }

                adapter = new PatientSearchServicesListAdapter(servicesList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PatientSearchServicesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
