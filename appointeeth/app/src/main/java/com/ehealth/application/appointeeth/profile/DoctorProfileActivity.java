package com.ehealth.application.appointeeth.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.login.BottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editclinique.CliniqueBottomSheetDialog;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.RemoveCliniqueActivity;

public class DoctorProfileActivity extends AppCompatActivity {

    ImageView cliniquesEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        cliniquesEditButton = findViewById(R.id.cliniques_edit_button);

        cliniquesEditButton.setOnClickListener(view -> {
            CliniqueBottomSheetDialog bottomSheet = new CliniqueBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "CliniqueBottomSheet");
        });

    }
}