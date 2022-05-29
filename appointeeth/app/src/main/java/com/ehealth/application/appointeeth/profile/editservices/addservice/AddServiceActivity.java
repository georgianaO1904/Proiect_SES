package com.ehealth.application.appointeeth.profile.editservices.addservice;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.data.models.UserObject;
import com.ehealth.application.appointeeth.profile.editclinique.addclinique.AddCliniqueActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AddServiceActivity extends AppCompatActivity {
    private EditText serviceName, servicePrice, serviceDescription, servicePriceExisting;
    private Button submitButton, submitExistingButton;
    private String userId;
    Spinner dropdownExistingServices;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        // Dropdown for existing services
        dropdownExistingServices = findViewById(R.id.dropdown_existing_services);
        ArrayList<String> dropdownValues = new ArrayList<>();
        HashMap<String, String > mapServiceNametoServiceId = new HashMap<>();
        HashMap<String, String > mapServiceIdtoServiceDescription = new HashMap<>();
        dropdownValues.add("Choose service...");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("services");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot ds2: ds.getChildren()) {
                        if (ds2.getKey().compareTo("name") == 0) {
                            dropdownValues.add((String)ds2.getValue());
                            mapServiceNametoServiceId.put((String)ds2.getValue(), ds.getKey());
                        } else if (ds2.getKey().compareTo("description") == 0) {
                            mapServiceIdtoServiceDescription.put(ds.getKey(), (String)ds2.getValue());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownExistingServices.setAdapter(adapter);

        serviceName = findViewById(R.id.add_service_name);
        servicePrice = findViewById(R.id.add_service_price);
        servicePriceExisting = findViewById(R.id.add_service_price_existing);
        serviceDescription = findViewById(R.id.add_service_description);
        submitButton = findViewById(R.id.submit_service_button);
        submitExistingButton = findViewById(R.id.submit_service_existing);


        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        submitExistingButton.setOnClickListener(view -> {
            final String name = dropdownExistingServices.getSelectedItem().toString();
            final String price = servicePriceExisting.getText().toString();

            if (name.isEmpty() || name.compareTo("Choose service...") == 0) {
                ((TextView)dropdownExistingServices.getSelectedView()).setError("Please enter an existing service name");
                dropdownExistingServices.requestFocus();
            } else if (price.isEmpty()) {
                servicePriceExisting.setError("Please enter a price for the service");
                servicePriceExisting.requestFocus();
            }  else {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

                String serviceId = mapServiceNametoServiceId.get(name);
                String serviceDescriotion = mapServiceIdtoServiceDescription.get(serviceId);

                dbRef.child("services").child(serviceId).child("doctors").child(userId).setValue(price);
                dbRef.child("users").child(userId).child("services").child(serviceId).setValue(new ServiceObject(serviceId, name, price, serviceDescriotion)).addOnCompleteListener( task -> {

                            if(task.isSuccessful()){
                                Toast.makeText(AddServiceActivity.this, "The new service has been added successfully!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AddServiceActivity.this, "There was an error, please try again later!", Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });

        submitButton.setOnClickListener(view -> {
            final String name = serviceName.getText().toString();
            final String price = servicePrice.getText().toString();
            final String description = serviceDescription.getText().toString();

            if (name.isEmpty()) {
                serviceName.setError("Please enter service name");
                serviceName.requestFocus();
            } else if (price.isEmpty()) {
                servicePrice.setError("Please enter service price");
                servicePrice.requestFocus();
            } else if (description.isEmpty()) {
                serviceDescription.setError("Please enter service description");
                serviceDescription.requestFocus();
            } else {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

                // generare id nou pt obiectul serviciu
                String id = dbRef.child("users").child(userId).child("services").push().getKey();

                dbRef.child("services").child(id).setValue(new ServiceObject(id, name, description));
                dbRef.child("services").child(id).child("doctors").child(userId).setValue(price);
                dbRef.child("users").child(userId).child("services").child(id).setValue(new ServiceObject(id, name, price, description)).addOnCompleteListener( task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(AddServiceActivity.this, "The new service has been added successfully!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AddServiceActivity.this, "There was an error, please try again later!", Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });




    }
}
