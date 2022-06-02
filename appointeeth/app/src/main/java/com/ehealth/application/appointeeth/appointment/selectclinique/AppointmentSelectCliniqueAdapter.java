package com.ehealth.application.appointeeth.appointment.selectclinique;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;

import java.util.ArrayList;

public class AppointmentSelectCliniqueAdapter extends RecyclerView.Adapter<AppointmentSelectCliniqueAdapter.AppointmentSelectCliniqueListViewHolder> {

    ArrayList<CliniqueObject> cliniqueList;
    String doctorId;

    public AppointmentSelectCliniqueAdapter(ArrayList<CliniqueObject> cliniqueList, String doctorId) {
        this.cliniqueList = cliniqueList;
        this.doctorId = doctorId;
    }

    public AppointmentSelectCliniqueAdapter.AppointmentSelectCliniqueListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_appointment_select_clinique, parent, false);
        return new AppointmentSelectCliniqueAdapter.AppointmentSelectCliniqueListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentSelectCliniqueAdapter.AppointmentSelectCliniqueListViewHolder holder, int position)
    {
        holder.cliniqueName.setText(cliniqueList.get(position).getName());
        holder.cliniqueLocation.setText(cliniqueList.get(position).getLocation());
        holder.selectClinique.setOnClickListener(view -> {
            String cliniqueId = cliniqueList.get(position).getId();
            System.out.println("AppointmentSelectCliniqueAdapter, cliniqueId="+cliniqueId);

            Intent intent = new Intent(view.getContext(), com.ehealth.application.appointeeth.appointment.selecttimeslots.AppointmentSelectTimeslotActivity.class);
            intent.putExtra("cliniqueId", cliniqueId);
            intent.putExtra("doctorId", doctorId);
            view.getContext().startActivity(intent);
        });
    }

    public int getItemCount(){
        if(cliniqueList == null)
            return  0;
        return cliniqueList.size();
    }
    static class AppointmentSelectCliniqueListViewHolder extends AppointmentSelectCliniqueViewHolder {
        public Button selectClinique;

        public AppointmentSelectCliniqueListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.selectClinique = itemView.findViewById(R.id.selectClinique);
        }
    }
}
