package com.ehealth.application.appointeeth.profile.editservices.removeservice;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName;
    public TextView textViewPrice;
    public TextView textViewDescription;
    public Button buttonDelete;

    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.service_name);
        this.textViewPrice = itemView.findViewById(R.id.service_price);
        this.textViewDescription = itemView.findViewById(R.id.service_description);
        this.buttonDelete = itemView.findViewById(R.id.deleteService);
    }
}
