package com.ehealth.application.appointeeth.profile.listcliniques;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;

public class CliniqueProfileViewHolder extends RecyclerView.ViewHolder{
    public TextView textViewName;
    public TextView editScheduleView;

    public CliniqueProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.name);
        this.editScheduleView = itemView.findViewById(R.id.edit_schedule);
    }
}
