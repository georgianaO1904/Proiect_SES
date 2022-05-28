package com.ehealth.application.appointeeth.profile.editservices.removeservice;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ServiceListViewHolder>{

    ArrayList<ServiceObject> serviceList;

    public ServiceListAdapter(ArrayList<ServiceObject> serviceList){
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceListAdapter.ServiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service, parent, false);
        return new ServiceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListAdapter.ServiceListViewHolder holder, int position) {
        holder.textViewName.setText(serviceList.get(position).getName());
        holder.textViewPrice.setText(serviceList.get(position).getPrice());
        holder.textViewDescription.setText(serviceList.get(position).getDescription());

        final ServiceObject service = serviceList.get(position);
        final ServiceViewHolder viewHolder = holder;

        holder.buttonDelete.setOnClickListener(v -> {
            String serviceId = service.getId();
            String userId =  FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("services").child(serviceId);

            dbRef.removeValue().addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(viewHolder.buttonDelete.getContext(), service.getName() + " was deleted!", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    static class ServiceListViewHolder extends ServiceViewHolder {
        public Button deleteService;

        public ServiceListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deleteService = itemView.findViewById(R.id.deleteService);
        }
    }
}
