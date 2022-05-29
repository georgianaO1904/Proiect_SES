package com.ehealth.application.appointeeth.profile.listcliniques;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.R;
import android.widget.Button;

import androidx.annotation.NonNull;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.profile.editworkhours.WorkHoursActivity;


import java.util.ArrayList;

public class CliniqueProfileListAdapter extends RecyclerView.Adapter<CliniqueProfileListAdapter.CliniqueProfileListViewHolder> {

    ArrayList<CliniqueObject> cliniquesList;

    public CliniqueProfileListAdapter(ArrayList<CliniqueObject> cliniqueList){
        this.cliniquesList = cliniqueList;
    }

    @NonNull
    @Override
    public CliniqueProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile_clinique, parent, false);
        return new CliniqueProfileListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CliniqueProfileListViewHolder holder, int position) {
        holder.textViewName.setText(cliniquesList.get(position).getName());
        holder.editScheduleView.setOnClickListener(view -> {
            String cliniqueId = cliniquesList.get(position).getId();
            Intent intent = new Intent(view.getContext(), WorkHoursActivity.class);
            intent.putExtra("cliniqueId", cliniqueId);
            view.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        if (cliniquesList == null)
            return 0;
        return cliniquesList.size();
    }

    static class CliniqueProfileListViewHolder extends CliniqueProfileViewHolder {
        public Button deleteClinique;

        public CliniqueProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deleteClinique = itemView.findViewById(R.id.deleteClinique);
        }
    }
}
