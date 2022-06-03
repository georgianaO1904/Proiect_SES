package com.ehealth.application.appointeeth.appointment.view_appointment;

import android.view.View;
import android.widget.TextView;
import com.ehealth.application.appointeeth.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorViewAppointmentViewHolder extends RecyclerView.ViewHolder {
    public TextView patientName, patientPhoneNr, service, clinique, timeslot;

    public DoctorViewAppointmentViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.patientName = itemView.findViewById(R.id.patientName);
        this.patientPhoneNr = itemView.findViewById(R.id.patientPhoneNr);
        this.service = itemView.findViewById(R.id.appService);
        this.clinique = itemView.findViewById(R.id.appClinique);
        this.timeslot = itemView.findViewById(R.id.timeslot);
    }
}
