package com.ehealth.application.appointeeth.appointment.selectclinique;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentSelectCliniqueViewHolder extends RecyclerView.ViewHolder {
    public TextView cliniqueName, cliniqueLocation;
    public Button selectCliniqueBtn;

    public AppointmentSelectCliniqueViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.cliniqueName = itemView.findViewById(R.id.cliniqueName);
        this.cliniqueLocation = itemView.findViewById(R.id.cliniqueLocation);
        this.selectCliniqueBtn = itemView.findViewById(R.id.selectClinique);
    }
}
