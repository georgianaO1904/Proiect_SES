package com.ehealth.application.appointeeth.profile.searchservices;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;

public class PatientSearchServicesViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName;
    public TextView textViewDescription;
    public Button buttonSelect;

    public PatientSearchServicesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.name);
        this.textViewDescription = itemView.findViewById(R.id.description);
        this.buttonSelect = itemView.findViewById(R.id.selectService);
    }


}
