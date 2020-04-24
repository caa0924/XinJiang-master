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
public class TroubleFragment extends Fragment {

    static TroubleFragment troubleFragment;

    public static TroubleFragment newInstance() {
        if (troubleFragment == null) {
            troubleFragment = new TroubleFragment();
        }
        return troubleFragment;
    }

    public TroubleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trouble, container, false);
    }

}
