package com.mhimine.jdk.operations_managementApp.Fragment.IndustrialVideo;


import android.content.Intent;
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
public class IndustrialVideoFragment extends Fragment implements View.OnClickListener {

    static IndustrialVideoFragment industrialVideoFragment;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    private View v;

    public static IndustrialVideoFragment newInstance() {
        if (industrialVideoFragment == null) {
            industrialVideoFragment = new IndustrialVideoFragment();
        }
        return industrialVideoFragment;
    }

    public IndustrialVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_industrial_video, container, false);
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
                if (getActivity() instanceof industrialVideoFragmentListener) {
                    ((industrialVideoFragmentListener) getActivity()).industrialVideoFragment();
                }
                break;
            default:
                break;
        }
    }
    public interface industrialVideoFragmentListener{
        public void industrialVideoFragment();
    }
}
