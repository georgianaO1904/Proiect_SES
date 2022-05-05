package com.ehealth.application.appointeeth.profile.editworkhours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueListAdapter;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.RemoveCliniqueActivity;
import com.ehealth.application.appointeeth.profile.editworkhours.cliniqueselectadapter.CliniqueSelectListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectCliniqueActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CliniqueSelectListAdapter adapter;
    ArrayList<CliniqueObject> cliniqueList;
    DatabaseReference dbRef;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clinique);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("cliniques");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cliniqueList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        cliniqueList.add(ds.getValue(CliniqueObject.class));
                    }
                }

                adapter = new CliniqueSelectListAdapter(cliniqueList, SelectCliniqueActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SelectCliniqueActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}