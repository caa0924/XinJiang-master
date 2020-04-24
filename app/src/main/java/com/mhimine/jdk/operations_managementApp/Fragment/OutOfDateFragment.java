package com.mhimine.jdk.operations_managementApp.Fragment;


import android.content.Context;
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
public class OutOfDateFragment extends Fragment implements View.OnClickListener {
    static OutOfDateFragment outOfDateFragment;
    @Bind(R.id.back_OutOfDate)
    ImageView iv_back_OutOfDate;
    private View view;
    BackOutOfDateListenter backOutOfDateListenter;

    public OutOfDateFragment() {
        // Required empty public constructor
    }

    public static OutOfDateFragment newInstance() {
        if (outOfDateFragment == null) {
            outOfDateFragment = new OutOfDateFragment();
        }
        return outOfDateFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_out_of_date, container, false);
        ButterKnife.bind(this, view);
        iv_back_OutOfDate.setOnClickListener(this);

        return view;
    }

    public interface BackOutOfDateListenter {
        void BackOutOfDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_OutOfDate:
                backOutOfDateListenter.BackOutOfDate();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        backOutOfDateListenter = (BackOutOfDateListenter) context;
    }
}
