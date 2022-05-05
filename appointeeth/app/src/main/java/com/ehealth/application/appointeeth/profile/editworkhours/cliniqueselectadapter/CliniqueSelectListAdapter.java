package com.ehealth.application.appointeeth.profile.editworkhours.cliniqueselectadapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.profile.editworkhours.WorkHoursActivity;

import java.util.ArrayList;

public class CliniqueSelectListAdapter extends RecyclerView.Adapter<CliniqueSelectListAdapter.CliniqueSelectViewHolder> {

    ArrayList<CliniqueObject> cliniquesList;
    Context context;

    public CliniqueSelectListAdapter(ArrayList<CliniqueObject> cliniqueList, Context context){
        this.cliniquesList = cliniqueList;
        this.context = context;
    }

    @NonNull
    @Override
    public CliniqueSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_clinique, parent, false);
        return new CliniqueSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CliniqueSelectViewHolder holder, int position) {
        holder.textViewLocation.setText(cliniquesList.get(position).getLocation());
        holder.textViewName.setText(cliniquesList.get(position).getName());

        final CliniqueObject clinique = cliniquesList.get(position);

        holder.buttonSelect.setOnClickListener(v -> {
            String cliniqueId = clinique.getId();

            Intent intent = new Intent(context, WorkHoursActivity.class);
            intent.putExtra("cliniqueId", cliniqueId);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return cliniquesList.size();
    }

    static class CliniqueSelectViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewLocation;
        public Button buttonSelect;

        public CliniqueSelectViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.name);
            this.textViewLocation = itemView.findViewById(R.id.location);
            this.buttonSelect = itemView.findViewById(R.id.select_clinique);
        }
    }
}

