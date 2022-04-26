package com.ehealth.application.appointeeth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.register.RegisterDoctorActivity;
import com.ehealth.application.appointeeth.register.RegisterPatientActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);

        TextView doctorRegisterButton = v.findViewById(R.id.doctor_register_button);
        TextView patientRegisterButton = v.findViewById(R.id.patient_register_button);

        doctorRegisterButton.setOnClickListener(view -> navigateToRegisterDoctor());
        patientRegisterButton.setOnClickListener( view -> navigateToRegisterPatient());

        return v;
    }

    private void navigateToRegisterDoctor() {
        Intent intent = new Intent(getActivity(), RegisterDoctorActivity.class);
        startActivity(intent);
    }

    private void navigateToRegisterPatient() {
        Intent intent = new Intent(getActivity(), RegisterPatientActivity.class);

        startActivity(intent);
    }
}
