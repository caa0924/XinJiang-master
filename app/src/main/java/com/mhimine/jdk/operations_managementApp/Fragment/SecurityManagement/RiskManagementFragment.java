package com.mhimine.jdk.operations_managementApp.Fragment.SecurityManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiskManagementFragment extends Fragment {

    static RiskManagementFragment riskManagementFragment;

    public static RiskManagementFragment newInstance() {
        if (riskManagementFragment == null) {
            riskManagementFragment = new RiskManagementFragment();
        }
        return riskManagementFragment;
    }

    public RiskManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_risk_management, container, false);
    }

}
