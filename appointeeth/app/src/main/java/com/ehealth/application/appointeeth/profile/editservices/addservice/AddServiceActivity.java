package com.ehealth.application.appointeeth.profile.editservices.addservice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.data.models.CliniqueObject;
import com.ehealth.application.appointeeth.data.models.ServiceObject;
import com.ehealth.application.appointeeth.profile.editclinique.addclinique.AddCliniqueActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServiceActivity extends AppCompatActivity {
    private EditText serviceName, servicePrice, serviceDescription;
    private Button submitButton;
    private ProgressBar progressBar;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        serviceName = findViewById(R.id.add_service_name);
        servicePrice = findViewById(R.id.add_service_price);
        serviceDescription = findViewById(R.id.add_service_description);
        submitButton = findViewById(R.id.submit_service_button);
        progressBar = findViewById(R.id.progressbar);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

                // generare id nou pt obiectul serviciu
                String id = dbRef.child("users").child(userId).child("services").push().getKey();
                ServiceObject newService = new ServiceObject(id, name, price, description);

                dbRef.child("users").child(userId).child("services").child(id).setValue(newService).addOnCompleteListener( task -> {
                            progressBar.setVisibility(View.INVISIBLE);

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
