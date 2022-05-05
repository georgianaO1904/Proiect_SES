package com.ehealth.application.appointeeth.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editworkhours.SelectCliniqueActivity;

public class DoctorProfileActivity extends AppCompatActivity {

    ImageView cliniquesEditButton, workhoursEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        cliniquesEditButton = findViewById(R.id.cliniques_edit_button);
        workhoursEditButton = findViewById(R.id.workhours_edit_button);

        workhoursEditButton.setOnClickListener(view -> {
           startActivity(new Intent(DoctorProfileActivity.this, SelectCliniqueActivity.class));
        });

        cliniquesEditButton.setOnClickListener(view -> {
            CliniqueBottomSheetDialog bottomSheet = new CliniqueBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "CliniqueBottomSheet");
        });

    }
}