package com.ehealth.application.appointeeth.appointment.selectclinique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentSelectCliniqueActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AppointmentSelectCliniqueAdapter adapter;
    String doctorId, cliniqueId, cliniqueName, cliniqueLocation;
    ArrayList<CliniqueObject> cliniqueList;
    private Button selectBtn;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_select_clinique);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        selectBtn = (Button)findViewById(R.id.selectClinique);
        doctorId = getIntent().getExtras().get("doctorId").toString();
        System.out.println("appointment activity, doctorId=" + doctorId);


        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(doctorId).child("cliniques");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cliniqueList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        cliniqueList.add(ds.getValue(CliniqueObject.class));
                    }
                    for(CliniqueObject clinique: cliniqueList){
//                        System.out.println("clinique.name = "+ clinique.getName());
//                        System.out.println("clinique.location = "+ clinique.getLocation());
                        cliniqueName = clinique.getName();
                        cliniqueLocation = clinique.getLocation();
                        cliniqueId = clinique.getId();
                        System.out.println("AppointmentActivity, cliniqueId = "+ clinique.getId());
                    }

                }
                adapter = new AppointmentSelectCliniqueAdapter(cliniqueList, doctorId);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentSelectCliniqueActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}