package com.mhimine.jdk.operations_managementApp.Fragment;


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
import com.mhimine.jdk.operations_managementApp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DispatchingManagementFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    static DispatchingManagementFragment dispatchingManagementFragment;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    @Bind(R.id.view_pager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentlist = new ArrayList<>();
    private View v;
    private myFragmentAdapter adapter;

    public DispatchingManagementFragment() {
        // Required empty public constructor
    }

    public static DispatchingManagementFragment newInstance() {
        if (dispatchingManagementFragment == null) {
            dispatchingManagementFragment = new DispatchingManagementFragment();
        }
        return dispatchingManagementFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_dispatching_management, container, false);
        ButterKnife.bind(this, v);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        initFragmentList();
        initWidgets();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawerIcon:
                if (getActivity() instanceof dispatchingManagementFragmentListener) {
                    ((dispatchingManagementFragmentListener) getActivity()).dispatchingManagementFragment();
                }
                break;
            default:
                break;
        }
    }

    private void initFragmentList() {
        titleList.clear();
        fragmentlist.clear();
        titleList.add("综合日报");
        titleList.add("销售日报");
        titleList.add("生产日报");
        titleList.add("掘进日报");
        titleList.add("排班系统");
        titleList.add("值班系统");
        fragmentlist.add(ComprehensiveDailyFragment.newInstance());
        fragmentlist.add(SalesDailyFragment.newInstance());
        fragmentlist.add(ProductionDailyFragment.newInstance());
        fragmentlist.add(TunnelingDailyFragment.newInstance());
        fragmentlist.add(DutySystemFragment.newInstance());
        fragmentlist.add(SchedulingSystemFragment.newInstance());

    }

    private void initWidgets() {
        adapter = new myFragmentAdapter(getChildFragmentManager());
        // getFragmentManager();
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewpager);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        viewpager.setCurrentItem(0);
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

    public interface dispatchingManagementFragmentListener {
        public void dispatchingManagementFragment();
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
