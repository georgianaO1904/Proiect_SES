package com.ehealth.application.appointeeth.appointment.view_appointment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.appointment.selecttimeslots.AppointmentSelectTimeslotActivity;
import com.ehealth.application.appointeeth.appointment.selecttimeslots.AppointmentSelectTimeslotAdapter;
import com.ehealth.application.appointeeth.data.models.AppointmentObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorViewAppointmentActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    DoctorViewAppointmentAdapter adapter;
    String clinique, timeslot, service, doctorId, patientId;
    ArrayList<AppointmentObject> appointmentsList;
    DatabaseReference dbRef;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_appointments);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        doctorId = FirebaseAuth.getInstance().getCurrentUser().getUid();
       //patientId = getIntent().getExtras().get("patientId").toString();

        System.out.println("DoctorViewAppointmentActivity, patientId"+patientId);

        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(doctorId).child("appointments");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    appointmentsList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        appointmentsList.add(ds.getValue(AppointmentObject.class));
                        System.out.println("appointmentsList="+appointmentsList);
                    }
                    for(AppointmentObject appointment: appointmentsList){
                        clinique = appointment.getClinique();
                        timeslot = appointment.getTimeslot();
                        service = appointment.getService();
                    }
                }
//                //create adapter
                //recyclerView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorViewAppointmentActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
