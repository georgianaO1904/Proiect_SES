package com.ehealth.application.appointeeth.appointment.selecttimeslots;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;

public class AppointmentSelectTimeslotViewHolder extends RecyclerView.ViewHolder {
    public TextView timeslot;
    public Button selectTimeslot;

    public AppointmentSelectTimeslotViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.timeslot = itemView.findViewById(R.id.timeslot);
        this.selectTimeslot = itemView.findViewById(R.id.selectTimeslot);
    }
}
