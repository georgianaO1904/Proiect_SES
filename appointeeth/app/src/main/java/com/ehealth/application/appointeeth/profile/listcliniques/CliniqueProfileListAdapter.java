package com.ehealth.application.appointeeth.profile.listcliniques;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    }


    @Override
    public int getItemCount() {
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
