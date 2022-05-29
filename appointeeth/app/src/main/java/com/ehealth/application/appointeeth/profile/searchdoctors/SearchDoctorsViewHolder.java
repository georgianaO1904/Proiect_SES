package com.ehealth.application.appointeeth.profile.searchdoctors;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;

public class SearchDoctorsViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName, textViewPrice;
    public Button buttonSelect;

    public SearchDoctorsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.name);
        this.textViewPrice = itemView.findViewById(R.id.srv_price);
        this.buttonSelect = itemView.findViewById(R.id.selectDoctor);
    }

}
