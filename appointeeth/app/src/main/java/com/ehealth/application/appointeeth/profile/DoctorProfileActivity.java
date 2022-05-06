package com.ehealth.application.appointeeth.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editworkhours.SelectCliniqueActivity;

public class DoctorProfileActivity extends AppCompatActivity {

    TextView workhoursEditView, cliniquesEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        cliniquesEditView = findViewById(R.id.cliniques_edit_view);
        workhoursEditView = findViewById(R.id.workhours_edit_view);

        workhoursEditView.setOnClickListener(view -> {
           startActivity(new Intent(DoctorProfileActivity.this, SelectCliniqueActivity.class));
        });

        cliniquesEditView.setOnClickListener(view -> {
            CliniqueBottomSheetDialog bottomSheet = new CliniqueBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "CliniqueBottomSheet");
        });

    }
}