package com.ehealth.application.appointeeth.profile.editworkhours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.TimeSlot;
import com.ehealth.application.appointeeth.login.LoginActivity;
import com.ehealth.application.appointeeth.profile.editworkhours.timeslotsadapter.TimeSlotsListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Set;

import static com.ehealth.application.appointeeth.data.Constants.TIME_SLOTS_ARRAY;

public class WorkHoursActivity extends AppCompatActivity {

    Button submitButton;
    RecyclerView recyclerView;
    TimeSlotsListAdapter adapter;
    ArrayList<TimeSlot> timeSlotsList;
    DatabaseReference dbRef;
    String userId, cliniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_hours);

        recyclerView = findViewById(R.id.recyclerView);
        submitButton = findViewById(R.id.submit_button);

        // extract clinique id sent from previous screen
        cliniqueId = getIntent().getExtras().get("cliniqueId").toString();
        // get userId from Firebase
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        timeSlotsList = new ArrayList<TimeSlot>();
        for(int i = 0; i < TIME_SLOTS_ARRAY.size(); i++) {
            timeSlotsList.add(new TimeSlot(TIME_SLOTS_ARRAY.get(i), false));
        }

        // set recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new TimeSlotsListAdapter(timeSlotsList);
        recyclerView.setAdapter(adapter);

        // todo: add doctor program inside database
        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("program");

        submitButton.setOnClickListener( v -> {
            // get only the selected timeslots from the adapter
            ArrayList<String> selectedTimeSlotsSet = adapter.getSelectedTimeSlots();
            // insert the timeslots under users/{userId}/program/{cliniqueId}
            dbRef.child(cliniqueId).setValue(selectedTimeSlotsSet).addOnCompleteListener(task -> {
                if(task.isSuccessful())  {
                   Toast.makeText(WorkHoursActivity.this, "Program set successfully!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
