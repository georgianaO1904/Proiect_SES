package com.ehealth.application.appointeeth.profile.searchservices;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueListAdapter;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueViewHolder;
import com.ehealth.application.appointeeth.profile.editworkhours.WorkHoursActivity;
import com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PatientSearchServicesListAdapter extends RecyclerView.Adapter<PatientSearchServicesListAdapter.PatientSearchServicesListViewHolder>{

    ArrayList<ServiceObject> servicesList;

    public PatientSearchServicesListAdapter( ArrayList<ServiceObject> servicesList){
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public PatientSearchServicesListAdapter.PatientSearchServicesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search_service, parent, false);
        return new PatientSearchServicesListAdapter.PatientSearchServicesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientSearchServicesListAdapter.PatientSearchServicesListViewHolder holder, int position) {
        holder.textViewName.setText(servicesList.get(position).getName());
        holder.textViewDescription.setText(servicesList.get(position).getDescription());

        final ServiceObject service = servicesList.get(position);
        final PatientSearchServicesViewHolder viewHolder = holder;

        holder.buttonSelect.setOnClickListener(v -> {
            String serviceId = service.getId();

            Intent intent = new Intent(v.getContext(), SearchDoctorsActivity.class);
            intent.putExtra("serviceId", serviceId);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    static class PatientSearchServicesListViewHolder extends PatientSearchServicesViewHolder {
        public Button selectService;

        public PatientSearchServicesListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.selectService = itemView.findViewById(R.id.selectService);
        }
    }
}
