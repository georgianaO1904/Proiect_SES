package com.ehealth.application.appointeeth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueActivity;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.listservices.ServiceProfileListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadSelectedDoctorPage extends AppCompatActivity {
    private String uid;
    private FirebaseAuth mFirebaseAuth;

    private TextView doctorName, doctorEmail;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    RecyclerView servicesRecyclerView;
    ArrayList<ServiceObject> serviceList;
    ServiceProfileListAdapter servicesAdapter;
    DatabaseReference dbRefCliniques, dbRefServices;
    String userId, doctorId;
    private Button servicesBtn, cliniquesBtn, appointmentBtn;
    //CliniqueProfileListAdapter cliniquesAdapter;
    ArrayList<CliniqueObject> cliniqueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_selected_doctor_page);

//        servicesRecyclerView = findViewById(R.id.doctor_services_recyclerview);
//        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        servicesBtn = (Button) findViewById(R.id.services_button);
        cliniquesBtn = (Button) findViewById(R.id.cliniques_button);
        appointmentBtn = (Button) findViewById(R.id.appointment_button);

        doctorId = getIntent().getExtras().get("doctorId").toString();
        userId = doctorId;

        doctorName = (TextView) findViewById(R.id.doctor_fullname);

        mFirebaseAuth = FirebaseAuth.getInstance();
        uid = userId;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("users").child(uid);

        servicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               System.out.println("services popup");
               System.out.println("services:"+serviceList);
            }
        });

        cliniquesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("cliniques popup");
                System.out.println("cliniques: " + cliniqueList);
            }
        });

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AppointmentSelectCliniqueActivity.class);
                intent.putExtra("doctorId", doctorId);
                System.out.println("Load doctor profile page, doctorId = "+ doctorId);
                view.getContext().startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserObject user = (UserObject) snapshot.getValue(UserObject.class);
                doctorName.setText(user.getUserName());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

