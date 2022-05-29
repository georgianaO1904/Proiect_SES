package com.ehealth.application.appointeeth.profile.listservices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.ServiceObject;

import java.util.ArrayList;

public class ServiceProfileListAdapter extends RecyclerView.Adapter<ServiceProfileListAdapter.ServiceProfileListViewHolder>{

    ArrayList<ServiceObject> serviceList;

    public ServiceProfileListAdapter(ArrayList<ServiceObject> serviceList){
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceProfileListAdapter.ServiceProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile_service, parent, false);
        return new ServiceProfileListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProfileListAdapter.ServiceProfileListViewHolder holder, int position) {
        holder.textViewName.setText(serviceList.get(position).getName());
        holder.textViewPrice.setText(serviceList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        if (serviceList == null)
            return 0;
        return serviceList.size();
    }

    static class ServiceProfileListViewHolder extends ServiceProfileViewHolder {
        public Button deleteService;

        public ServiceProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deleteService = itemView.findViewById(R.id.deleteService);
        }
    }
}

