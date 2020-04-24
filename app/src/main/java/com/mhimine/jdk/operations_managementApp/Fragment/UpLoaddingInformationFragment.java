package com.mhimine.jdk.operations_managementApp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.R;


/**
 * Created by JDK on 2016/8/4.
 */
public class UpLoaddingInformationFragment extends Fragment {
    private View view;


    public static UpLoaddingInformationFragment newInstance(Context context){
        UpLoaddingInformationFragment singleFragment =new UpLoaddingInformationFragment();
        return singleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_up_load_info, container, false);//得到对应的布局文件

        return view;
    }



}
