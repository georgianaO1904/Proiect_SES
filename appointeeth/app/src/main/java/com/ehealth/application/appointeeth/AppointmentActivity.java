package com.ehealth.application.appointeeth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueListAdapter;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.RemoveCliniqueActivity;
import com.ehealth.application.appointeeth.profile.editworkhours.cliniqueselectadapter.CliniqueSelectListAdapter;
import com.ehealth.application.appointeeth.profile.listcliniques.CliniqueProfileListAdapter;
import com.ehealth.application.appointeeth.profile.listcliniques.CliniqueProfileViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity {

    String doctorId, cliniqueId, cliniqueName, cliniqueLocation;
    ArrayList<CliniqueObject> cliniqueList;
    private Button selectBtn;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        selectBtn = (Button)findViewById(R.id.select_clinique);
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

                        //trimis id ul clinicii in pagina de select timeslot
                        selectBtn.setOnClickListener( v -> {
                            Intent intent = new Intent(v.getContext(), AppointmentSelectTimeSlot.class);
                            intent.putExtra("doctorId", doctorId);
                            v.getContext().startActivity(intent);
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}