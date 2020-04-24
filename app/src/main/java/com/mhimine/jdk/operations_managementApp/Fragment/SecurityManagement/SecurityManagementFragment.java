package com.mhimine.jdk.operations_managementApp.Fragment.SecurityManagement;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.ComprehensiveDailyFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.DutySystemFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.ProductionDailyFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.SalesDailyFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.SchedulingSystemFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagement.TunnelingDailyFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagementFragment;
import com.mhimine.jdk.operations_managementApp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecurityManagementFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    static SecurityManagementFragment securityManagementFragment;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    @Bind(R.id.view_pager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentlist = new ArrayList<>(6);
    private View v;
    private myFragmentAdapter adapter;

    public static SecurityManagementFragment newInstance() {
        if (securityManagementFragment == null) {
            securityManagementFragment = new SecurityManagementFragment();
        }
        return securityManagementFragment;
    }

    public SecurityManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_security_management, container, false);
        ButterKnife.bind(this, v);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        initFragmentList();
        initWidgets();
        return v;
    }
    private void initFragmentList() {
        titleList.clear();
        titleList.add("风险管控");
        titleList.add("隐患排查");
        titleList.add("三违管理");
        fragmentlist.add(RiskManagementFragment.newInstance());
        fragmentlist.add(ThreeViolationsFragment.newInstance());
        fragmentlist.add(TroubleFragment.newInstance());
    }

    private void initWidgets() {
        adapter = new myFragmentAdapter(getChildFragmentManager());
        // getFragmentManager();
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewpager);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        viewpager.setCurrentItem(0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawerIcon:
                if (getActivity() instanceof securityManagementFragmentListener) {
                    ((securityManagementFragmentListener) getActivity()).securityManagementFragment();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface securityManagementFragmentListener{
        public void securityManagementFragment();
    }
    public class myFragmentAdapter extends FragmentPagerAdapter {

        public myFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }
    }
}
