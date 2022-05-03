package com.ehealth.application.appointeeth.profile.editclinique.removeclinique;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.ehealth.application.appointeeth.R;
import android.widget.Button;
import androidx.annotation.NonNull;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;

import java.util.ArrayList;

// todo: finish implementation
// add item in the list
// and delete existing item
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
        //final CliniqueViewHolder endHolder = holder; // what s this for?

        holder.buttonDelete.setOnClickListener(v -> {
            /*String cliniqueId = clinique.getId();

            CliniqueObject updated_clinique = clinique;
            DatabaseReference cliniqueRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cliniques").child(cliniqueId);

            cliniqueRef.removeValue().addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){
                            //Toast.makeText(endHolder.deleteBook.getContext(), book.getBookName() + " was deleted!", Toast.LENGTH_LONG).show();
                        }
                    }
            );*/

        });
    }


    @Override
    public int getItemCount() {
        return cliniquesList.size();
    }

    class CliniqueListViewHolder extends CliniqueViewHolder {
        public Button deleteClinique;

        public CliniqueListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deleteClinique = (Button) itemView.findViewById(R.id.deleteClinique);
        }
    }
}
