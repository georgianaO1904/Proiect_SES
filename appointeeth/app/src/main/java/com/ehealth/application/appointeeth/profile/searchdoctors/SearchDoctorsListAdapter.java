package com.ehealth.application.appointeeth.profile.searchdoctors;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehealth.application.appointeeth.LoadSelectedDoctorPage;
import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueListAdapter;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.CliniqueViewHolder;
import com.ehealth.application.appointeeth.profile.editworkhours.WorkHoursActivity;
import com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsActivity;
import com.ehealth.application.appointeeth.profile.searchservices.PatientSearchServicesViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchDoctorsListAdapter extends RecyclerView.Adapter<com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsListAdapter.SearchDoctorsListViewHolder>{

    ArrayList<UserObject> doctorsList;
    String serviceId;
    public SearchDoctorsListAdapter( ArrayList<UserObject> doctorsList, String serviceId){
        this.doctorsList = doctorsList;
        this.serviceId = serviceId;
    }

    @NonNull
    @Override
    public com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsListAdapter.SearchDoctorsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search_doctor, parent, false);
        return new com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsListAdapter.SearchDoctorsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.ehealth.application.appointeeth.profile.searchdoctors.SearchDoctorsListAdapter.SearchDoctorsListViewHolder holder, int position) {
        final UserObject doctor = doctorsList.get(position);

        System.out.println(doctorsList.get(position).getServices());
        System.out.println("==="+doctorsList.get(position).getServices().get(serviceId).getPrice());
        holder.textViewName.setText(doctorsList.get(position).getUserName());
        holder.textViewPrice.setText(doctorsList.get(position).getServices().get(serviceId).getPrice());

        holder.buttonSelect.setOnClickListener(v -> {
            String doctorId = doctor.getUserId();

           // cv de genul pt partea cu deschisul paginii cuinfoptdoctor
            Intent intent = new Intent(v.getContext(), LoadSelectedDoctorPage.class);
            intent.putExtra("doctorId", doctorId);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    static class SearchDoctorsListViewHolder extends SearchDoctorsViewHolder {
        public Button selectDoctor;

        public SearchDoctorsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.selectDoctor = itemView.findViewById(R.id.selectDoctor);
        }
    }

}
