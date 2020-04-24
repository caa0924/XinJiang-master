package com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.child;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mhimine.jdk.operations_managementApp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CangWeiFragment extends Fragment implements View.OnClickListener {

    static CangWeiFragment cangWeiFragment;
    @Bind(R.id.back)
    ImageView back;

    public static CangWeiFragment newInstance() {
        if (cangWeiFragment == null) {
            cangWeiFragment = new CangWeiFragment();
        }
        return cangWeiFragment;
    }

    public CangWeiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cang_wei, container, false);
        ButterKnife.bind(this, view);
        back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                getFragmentManager().popBackStack();
        }
    }
}
