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
public class DutySystemFragment extends Fragment {
    static DutySystemFragment dutySystemFragment;

    public DutySystemFragment() {
        // Required empty public constructor
    }

    public static DutySystemFragment newInstance() {
        if (dutySystemFragment == null) {
            dutySystemFragment = new DutySystemFragment();
        }
        return dutySystemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_duty_system, container, false);
    }

}
