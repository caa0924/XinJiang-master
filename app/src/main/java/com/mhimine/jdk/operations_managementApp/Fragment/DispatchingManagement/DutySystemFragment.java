package com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhimine.jdk.operations_managementApp.Adapter.RecyclerAdapter;
import com.mhimine.jdk.operations_managementApp.Model.DataBean;
import com.mhimine.jdk.operations_managementApp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DutySystemFragment extends Fragment {
    static DutySystemFragment dutySystemFragment;
    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;
    private List<DataBean> dataBeanList;
    private DataBean dataBean;
    private RecyclerAdapter mAdapter;

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
        View view = inflater.inflate(R.layout.fragment_duty_system, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }
    /**
     * 模拟数据
     */
    private void initData(){
        dataBeanList = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            dataBean = new DataBean();
            dataBean.setID(i+"");
            dataBean.setType(0);
            if (i==0){
                dataBean.setParentLeftTxt("早班");
                dataBean.setChildLeftTxt("综采一队");
                dataBean.setChildLeftTxt("综采二队");
                dataBean.setChildLeftTxt("综掘一队");
                dataBean.setChildLeftTxt("综掘二队");
            }else if(i==1){
                dataBean.setParentLeftTxt("中班");
                dataBean.setChildLeftTxt("综采一队");
                dataBean.setChildLeftTxt("综采二队");
                dataBean.setChildLeftTxt("综掘一队");
                dataBean.setChildLeftTxt("综掘二队");
            }else{
                dataBean.setParentLeftTxt("夜班");
                dataBean.setChildLeftTxt("综采一队");
                dataBean.setChildLeftTxt("综采二队");
                dataBean.setChildLeftTxt("综掘一队");
                dataBean.setChildLeftTxt("综掘二队");
            }

            dataBean.setChildBean(dataBean);
            dataBeanList.add(dataBean);
        }
        setData();
    }

    private void setData(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecyclerAdapter(getContext(),dataBeanList);
        mRecyclerView.setAdapter(mAdapter);
        //滚动监听
        mAdapter.setOnScrollListener(new RecyclerAdapter.OnScrollListener() {
            @Override
            public void scrollTo(int pos) {
                mRecyclerView.scrollToPosition(pos);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
