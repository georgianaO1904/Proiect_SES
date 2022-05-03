package com.ehealth.application.appointeeth.profile.editclinique;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.ehealth.application.appointeeth.R;
import com.ehealth.application.appointeeth.profile.editclinique.addclinique.AddCliniqueActivity;
import com.ehealth.application.appointeeth.profile.editclinique.removeclinique.RemoveCliniqueActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CliniqueBottomSheetDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.clinique_sheet_dialog, container, false);

        TextView addCliniqueButton = v.findViewById(R.id.add_clinique_button);
        TextView removeCliniquesButton = v.findViewById(R.id.remove_cliniques_button);

        addCliniqueButton.setOnClickListener(view -> navigateToAddClinique());
        removeCliniquesButton.setOnClickListener( view -> navigateToRemoveClinique());

        return v;
    }

    private void navigateToRemoveClinique() {
        Intent intent = new Intent(getActivity(), RemoveCliniqueActivity.class);
        startActivity(intent);
    }

    private void navigateToAddClinique() {
        Intent intent = new Intent(getActivity(), AddCliniqueActivity.class);
        startActivity(intent);
    }
}
