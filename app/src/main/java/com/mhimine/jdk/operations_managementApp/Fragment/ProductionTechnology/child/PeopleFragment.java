package com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.child;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mhimine.jdk.operations_managementApp.Activity.MainActivity;
import com.mhimine.jdk.operations_managementApp.Fragment.ComprehensiveDetection.PeopleInJobFragment;
import com.mhimine.jdk.operations_managementApp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment implements View.OnClickListener {
    static PeopleFragment peopleFragment;
    @Bind(R.id.back)
    ImageView back;
    private BackProdutionTechnology backProdutionTechnology;

    public static PeopleFragment newInstance() {
        if (peopleFragment == null) {
            peopleFragment = new PeopleFragment();
        }
        return peopleFragment;
    }

    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);
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
        switch (v.getId()) {
            case R.id.back:
                backProdutionTechnology.backProduction();
        }

    }

    public interface BackProdutionTechnology {
        public void backProduction();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        backProdutionTechnology = (BackProdutionTechnology) context;
    }

}
