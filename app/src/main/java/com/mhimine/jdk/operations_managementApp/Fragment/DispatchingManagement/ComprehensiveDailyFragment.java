package com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprehensiveDailyFragment extends Fragment {

    static ComprehensiveDailyFragment comprehensiveDailyFragment;

    public ComprehensiveDailyFragment() {
        // Required empty public constructor
    }

    public static ComprehensiveDailyFragment newInstance() {
        if (comprehensiveDailyFragment == null) {
            comprehensiveDailyFragment = new ComprehensiveDailyFragment();
        }
        return comprehensiveDailyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comprehensive_daily, container, false);
    }

}
