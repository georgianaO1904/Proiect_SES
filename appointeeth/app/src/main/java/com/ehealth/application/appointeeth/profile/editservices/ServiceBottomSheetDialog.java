package com.ehealth.application.appointeeth.profile.editservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.profile.editservices.addservice.AddServiceActivity;
import com.ehealth.application.appointeeth.profile.editservices.removeservice.RemoveServiceActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ServiceBottomSheetDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.service_sheet_dialog, container, false);

        TextView addServiceButton = v.findViewById(R.id.add_services_button);
        TextView removeServiceButton = v.findViewById(R.id.remove_services_button);

        addServiceButton.setOnClickListener(view -> navigateToAddSerivce());
        removeServiceButton.setOnClickListener( view -> navigateToRemoveService());

        return v;
    }

    private void navigateToRemoveService() {
        Intent intent = new Intent(getActivity(), RemoveServiceActivity.class);
        startActivity(intent);
    }

    private void navigateToAddSerivce() {
        Intent intent = new Intent(getActivity(), AddServiceActivity.class);
        startActivity(intent);
    }
}
