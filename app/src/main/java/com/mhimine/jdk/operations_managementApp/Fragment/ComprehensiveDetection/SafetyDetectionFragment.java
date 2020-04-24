package com.mhimine.jdk.operations_managementApp.Fragment.ComprehensiveDetection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.SalesDailyFragment;
import com.mhimine.jdk.operations_managementApp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SafetyDetectionFragment extends Fragment {

    static SalesDailyFragment salesDailyFragment;

    public SafetyDetectionFragment() {
        // Required empty public constructor
    }
    public static SalesDailyFragment newInstance(){
        if (salesDailyFragment==null){
            salesDailyFragment=new SalesDailyFragment();
        }
        return salesDailyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_safety_detection, container, false);
    }

}
