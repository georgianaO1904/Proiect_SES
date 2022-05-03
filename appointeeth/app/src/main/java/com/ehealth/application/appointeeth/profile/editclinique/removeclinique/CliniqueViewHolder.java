package com.ehealth.application.appointeeth.profile.editclinique.removeclinique;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;

public class CliniqueViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName;
    public TextView textViewLocation;
    public Button buttonDelete;

    public CliniqueViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.name);
        this.textViewLocation = itemView.findViewById(R.id.location);
        this.buttonDelete = itemView.findViewById(R.id.deleteClinique);
    }
}
