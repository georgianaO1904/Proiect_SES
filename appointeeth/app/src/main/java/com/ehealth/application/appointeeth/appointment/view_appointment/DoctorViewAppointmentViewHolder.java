package com.ehealth.application.appointeeth.appointment.view_appointment;

import android.view.View;
import android.widget.TextView;
import com.ehealth.application.appointeeth.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorViewAppointmentViewHolder extends RecyclerView.ViewHolder {
    public TextView clinique, timeslot, service;

    public DoctorViewAppointmentViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.service = itemView.findViewById(R.id.service);
        this.clinique = itemView.findViewById(R.id.clinique);
        this.timeslot = itemView.findViewById(R.id.timeslot);
    }
}
