package com.ehealth.application.appointeeth.profile.editclinique.removeclinique;

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

public class CliniqueListAdapter extends RecyclerView.Adapter<CliniqueListAdapter.CliniqueListViewHolder> {

    ArrayList<CliniqueObject> cliniquesList;

    public CliniqueListAdapter( ArrayList<CliniqueObject> cliniqueList){
        this.cliniquesList = cliniqueList;
    }

    @NonNull
    @Override
    public CliniqueListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clinique, parent, false);
        return new CliniqueListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CliniqueListViewHolder holder, int position) {
        holder.textViewLocation.setText(cliniquesList.get(position).getLocation());
        holder.textViewName.setText(cliniquesList.get(position).getName());

        final CliniqueObject clinique = cliniquesList.get(position);
        final CliniqueViewHolder viewHolder = holder;

        holder.buttonDelete.setOnClickListener(v -> {
            String cliniqueId = clinique.getId();
            String userId =  FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cliniques").child(cliniqueId);

            dbRef.removeValue().addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){
                           Toast.makeText(viewHolder.buttonDelete.getContext(), clinique.getName() + " was deleted!", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        });
    }


    @Override
    public int getItemCount() {
        return cliniquesList.size();
    }

    static class CliniqueListViewHolder extends CliniqueViewHolder {
        public Button deleteClinique;

        public CliniqueListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deleteClinique = itemView.findViewById(R.id.deleteClinique);
        }
    }
}
