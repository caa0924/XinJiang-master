package com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhimine.jdk.operations_managementApp.Activity.MainActivity;
import com.mhimine.jdk.operations_managementApp.Fragment.Fragment1;
import com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.child.CangWeiFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.child.PeopleFragment;
import com.mhimine.jdk.operations_managementApp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductionTechnologyFragment extends Fragment implements View.OnClickListener {

    static ProductionTechnologyFragment productionTechnologyFragment;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    @Bind(R.id.cang_wei)
    ImageView cangWei;
    @Bind(R.id.people)
    ImageView people;
    @Bind(R.id.zong_cai)
    ImageView zongCai;
    @Bind(R.id.gong_shui)
    ImageView gongShui;
    @Bind(R.id.gong_dian)
    ImageView gongDian;
    @Bind(R.id.pai_shui)
    ImageView paiShui;
    @Bind(R.id.zhu_feng_ji)
    ImageView zhuFengJi;
    @Bind(R.id.wu_shui)
    ImageView wuShui;
    @Bind(R.id.bao_biao)
    ImageView baoBiao;
    private View view;
private  goToFragment go_to_fragment;
    private Fragment currentFragment;
    public static ProductionTechnologyFragment newInstance() {
        if (productionTechnologyFragment == null) {
            productionTechnologyFragment = new ProductionTechnologyFragment();
        }
        return productionTechnologyFragment;
    }

    public ProductionTechnologyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_production_technology, container, false);
        ButterKnife.bind(this, view);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        cangWei.setOnClickListener(this);
        people.setOnClickListener(this);
        gongDian.setOnClickListener(this);
        gongShui.setOnClickListener(this);
        wuShui.setOnClickListener(this);
        paiShui.setOnClickListener(this);
        baoBiao.setOnClickListener(this);
        zhuFengJi.setOnClickListener(this);
        zongCai.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawerIcon:
                if (getActivity() instanceof productionTechnologyFragmentListener) {
                    ((productionTechnologyFragmentListener) getActivity()).productionTechnologyFragment();
                }
                break;
            case  R.id.cang_wei:
                MainActivity.stack.push(new ProductionTechnologyFragment());
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearLayout2, new CangWeiFragment())
                        .addToBackStack(null) .commit();//将当前fragment加入到返回栈中
            case R.id.people:
                go_to_fragment.GotoPeopleFragment();
            default:
                break;
        }
    }

    public interface productionTechnologyFragmentListener {
        public void productionTechnologyFragment();
    }
    public interface goToFragment{
        public void GotoPeopleFragment();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        go_to_fragment = (goToFragment) context;
    }


}
