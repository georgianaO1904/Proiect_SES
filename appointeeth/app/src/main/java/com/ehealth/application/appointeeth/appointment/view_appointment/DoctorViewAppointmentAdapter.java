package com.ehealth.application.appointeeth.appointment.view_appointment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.appointment.selectclinique.AppointmentSelectCliniqueAdapter;

import java.util.ArrayList;

public class DoctorViewAppointmentAdapter extends RecyclerView.Adapter<DoctorViewAppointmentAdapter.DoctorViewAppointmentListHolder>{

    ArrayList<com.ehealth.application.appointeeth.data.models.AppointmentObject> appointmentsList;

    public DoctorViewAppointmentAdapter.DoctorViewAppointmentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_view_appointments, parent, false);
        return new DoctorViewAppointmentAdapter.DoctorViewAppointmentListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewAppointmentAdapter.DoctorViewAppointmentListHolder holder, int position)
    {
        holder.clinique.setText(appointmentsList.get(position).getClinique());
        holder.timeslot.setText(appointmentsList.get(position).getTimeslot());
        holder.service.setText(appointmentsList.get(position).getService());

//        holder.selectClinique.setOnClickListener(view -> {
//            String cliniqueId = cliniqueList.get(position).getId();
//            System.out.println("AppointmentSelectCliniqueAdapter, cliniqueId="+cliniqueId);
//
//            Intent intent = new Intent(view.getContext(), com.ehealth.application.appointeeth.appointment.selecttimeslots.AppointmentSelectTimeslotActivity.class);
//            intent.putExtra("cliniqueId", cliniqueId);
//            intent.putExtra("doctorId", doctorId);
//            intent.putExtra("serviceId", serviceId);
//
//            view.getContext().startActivity(intent);
//        });
    }

    public int getItemCount(){
        if(appointmentsList == null)
            return  0;
        return appointmentsList.size();
    }

    static class DoctorViewAppointmentListHolder extends DoctorViewAppointmentViewHolder{

        public DoctorViewAppointmentListHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

}
