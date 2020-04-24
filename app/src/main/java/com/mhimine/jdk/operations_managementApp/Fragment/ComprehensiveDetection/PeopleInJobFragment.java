package com.mhimine.jdk.operations_managementApp.Fragment.ComprehensiveDetection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleInJobFragment extends Fragment {

    static PeopleInJobFragment peopleInJobFragment;

    public static PeopleInJobFragment newInstance() {
        if (peopleInJobFragment == null) {
            peopleInJobFragment = new PeopleInJobFragment();
        }
        return peopleInJobFragment;
    }

    public PeopleInJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_in_job, container, false);
    }

}
