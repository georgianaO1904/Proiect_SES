package com.ehealth.application.appointeeth.appointment.selecttimeslots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueActivity;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueAdapter;
import com.ehealth.application.appointeeth.data.models.AppointmentObject;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.TimeSlot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentSelectTimeslotActivity extends AppCompatActivity {

    ArrayList<String> timeSlotList;
    String userId;
    String cliniqueId, doctorId, timeslot_value, serviceId;
    RecyclerView recyclerView;
    AppointmentSelectTimeslotAdapter adapter;
    private Button selectBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_select_timeslot);
        userId =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectBtn = (Button) findViewById(R.id.selectTimeslot);
        cliniqueId=getIntent().getExtras().get("cliniqueId").toString();
        doctorId = getIntent().getExtras().get("doctorId").toString();
        serviceId = getIntent().getExtras().get("serviceId").toString();

        System.out.println("AppointmentSelectTimeslotActivity, cliniqueId="+cliniqueId);
        System.out.println("AppointmentSelectTimeslotActivity, doctorId="+doctorId);

        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(doctorId).child("cliniques").child(cliniqueId).child("timeslots");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("1. inside onDataChange, AppointmentSelectTimeslotActivity");
                if (snapshot.exists()) {
                    System.out.println("2. inside onDataChange, AppointmentSelectTimeslotActivity");
                    timeSlotList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        timeSlotList.add(ds.getValue(String.class));
                        System.out.println("timeSlotList="+timeSlotList);
                    }
                    for(String timeSlot: timeSlotList){
                        timeslot_value = timeSlot;
                        System.out.println("AppointmentActivity, timeslot value  = "+ timeslot_value);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentSelectTimeslotActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(doctorId).child("appointments");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        AppointmentObject app = ds.getValue(AppointmentObject.class);
                        String cid = app.getClinique();
                        String ts = app.getTimeslot();
                        if (cid.compareTo(cliniqueId) == 0 && timeSlotList.contains(ts)) {
                            timeSlotList.remove(ts);
                        }
                    }
                }
                adapter = new AppointmentSelectTimeslotAdapter(timeSlotList, cliniqueId, doctorId, userId, serviceId);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentSelectTimeslotActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}