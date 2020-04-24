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
public class TunnelingDailyFragment extends Fragment {

    static TunnelingDailyFragment tunnelingDailyFragment;

    public TunnelingDailyFragment() {
        // Required empty public constructor
    }

    public static TunnelingDailyFragment newInstance() {
        if (tunnelingDailyFragment == null) {
            tunnelingDailyFragment = new TunnelingDailyFragment();
        }
        return tunnelingDailyFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tunneling_daily, container, false);
    }

}
