package com.ehealth.application.appointeeth.profile.searchdoctors;


import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.searchservices.PatientSearchServicesListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDoctorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchDoctorsListAdapter adapter;
    ArrayList<String> doctorsIdList;
    ArrayList<UserObject> doctorsList;

    DatabaseReference dbRef, dbRef2;
    String userId, serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctors);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // extract service id sent from previous screen
        serviceId = getIntent().getExtras().get("serviceId").toString();

        // get userId from Firebase
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("services").child(serviceId).child("doctors");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    doctorsIdList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        doctorsIdList.add(ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        dbRef2 = FirebaseDatabase.getInstance().getReference().child("users");

        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    doctorsList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (doctorsIdList.contains(ds.getKey()) )
                            doctorsList.add(ds.getValue(UserObject.class));
                    }
                }
                adapter = new SearchDoctorsListAdapter(doctorsList, serviceId);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

