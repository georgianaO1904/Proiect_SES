package com.ehealth.application.appointeeth.profile.editclinique.removeclinique;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ehealth.application.appointeeth.R;

public class RemoveCliniqueActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CliniqueListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_clinique);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CliniqueListAdapter(Data.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}