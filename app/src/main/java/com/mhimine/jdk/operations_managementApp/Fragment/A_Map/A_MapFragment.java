package com.mhimine.jdk.operations_managementApp.Fragment.A_Map;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhimine.jdk.operations_managementApp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class A_MapFragment extends Fragment implements View.OnClickListener {

    static A_MapFragment a_mapFragment;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    private View v;

    public static A_MapFragment newInstance() {
        if (a_mapFragment == null) {
            a_mapFragment = new A_MapFragment();
        }
        return a_mapFragment;
    }

    public A_MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_a__map, container, false);
        ButterKnife.bind(this, v);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawerIcon:
                if (getActivity() instanceof a_mapFragmentListener) {
                    ((a_mapFragmentListener) getActivity()).a_mapFragment();
                }
                break;
            default:
                break;
        }
    }
    public interface  a_mapFragmentListener{
        public void a_mapFragment();
    }
}
