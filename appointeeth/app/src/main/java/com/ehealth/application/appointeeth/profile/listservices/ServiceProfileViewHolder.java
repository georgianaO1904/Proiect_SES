package com.ehealth.application.appointeeth.profile.listservices;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;

public class ServiceProfileViewHolder extends RecyclerView.ViewHolder{
    public TextView textViewName;

    public ServiceProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.service_name);
    }
}
