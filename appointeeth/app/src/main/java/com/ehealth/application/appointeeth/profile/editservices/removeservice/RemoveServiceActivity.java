package com.ehealth.application.appointeeth.profile.editservices.removeservice;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ServiceListAdapter adapter;
    ArrayList<ServiceObject> serviceList;
    DatabaseReference dbRef;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_service);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("services");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    serviceList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        serviceList.add(ds.getValue(ServiceObject.class));
                    }
                }

                adapter = new ServiceListAdapter(serviceList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RemoveServiceActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
