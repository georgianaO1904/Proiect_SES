package com.ehealth.application.appointeeth.appointment.selecttimeslots;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.ehealth.application.appointeeth.AppointmentDoneActivity;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueAdapter;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueViewHolder;
import com.ehealth.application.appointeeth.data.models.AppointmentObject;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.TimeSlot;
import com.ehealth.application.appointeeth.profile.editclinique.addclinique.AddCliniqueActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentSelectTimeslotAdapter extends RecyclerView.Adapter<AppointmentSelectTimeslotAdapter.AppointmentSelectTimeslotListViewHolder> {
    ArrayList<String> timeSlotList;
    String cliniqueId, patientId, doctorId, serviceId;



    public AppointmentSelectTimeslotAdapter(ArrayList<String> timeSlotList, String cliniqueId, String doctorId,
                                            String patientId, String serviceId) {
        this.timeSlotList = timeSlotList;
        this.cliniqueId = cliniqueId;
        this.doctorId = doctorId;
        this.patientId =patientId;
    }
    static class AppointmentSelectTimeslotListViewHolder extends AppointmentSelectTimeslotViewHolder {
        public Button selectTimeslot;

        public AppointmentSelectTimeslotListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.selectTimeslot = itemView.findViewById(R.id.selectTimeslot);
        }
    }

    public AppointmentSelectTimeslotAdapter.AppointmentSelectTimeslotListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_appointment_select_timeslot, parent, false);
        return new AppointmentSelectTimeslotAdapter.AppointmentSelectTimeslotListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentSelectTimeslotAdapter.AppointmentSelectTimeslotListViewHolder holder, int position)
    {
        holder.timeslot.setText(timeSlotList.get(position));
        holder.selectTimeslot.setOnClickListener(view -> {
            String selectedTimeslot = timeSlotList.get(position);
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
            AppointmentObject newAppointment = new AppointmentObject(cliniqueId, selectedTimeslot, serviceId);
            dbRef.child("users").child(patientId).child("appointments").child(doctorId).setValue(newAppointment);
            dbRef.child("users").child(doctorId).child("appointments").child(patientId).setValue(newAppointment);
            Intent intent = new Intent(view.getContext(), AppointmentDoneActivity.class);
        });
    }

    public int getItemCount(){
        if(timeSlotList == null)
            return  0;
        return timeSlotList.size();
    }

}
