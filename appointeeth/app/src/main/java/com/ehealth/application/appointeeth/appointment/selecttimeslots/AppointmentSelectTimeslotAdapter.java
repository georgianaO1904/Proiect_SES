package com.ehealth.application.appointeeth.appointment.selecttimeslots;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ehealth.application.appointeeth.AppointmentDoneActivity;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueAdapter;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueViewHolder;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.TimeSlot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentSelectTimeslotAdapter extends RecyclerView.Adapter<AppointmentSelectTimeslotAdapter.AppointmentSelectTimeslotListViewHolder> {
    ArrayList<String> timeSlotList;
    String cliniqueId;


    public AppointmentSelectTimeslotAdapter(ArrayList<String> timeSlotList, String cliniqueId) {
        this.timeSlotList = timeSlotList;
        this.cliniqueId = cliniqueId;
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
        holder.selectTimeslot.setOnClickListener(view ->{
            Intent intent = new Intent(view.getContext(), AppointmentDoneActivity.class);
        });
    }

    public int getItemCount(){
        if(timeSlotList == null)
            return  0;
        return timeSlotList.size();
    }

}
