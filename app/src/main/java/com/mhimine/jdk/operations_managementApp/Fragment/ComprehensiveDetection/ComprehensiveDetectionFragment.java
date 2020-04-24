package com.mhimine.jdk.operations_managementApp.Fragment.ComprehensiveDetection;


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
public class ComprehensiveDetectionFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    static ComprehensiveDetectionFragment comprehensiveDetectionFragment;
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
    public static ComprehensiveDetectionFragment newInstance() {
        if (comprehensiveDetectionFragment == null) {
            comprehensiveDetectionFragment = new ComprehensiveDetectionFragment();
        }
        return comprehensiveDetectionFragment;
    }

    public ComprehensiveDetectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      v= inflater.inflate(R.layout.fragment_comprehensive_detection, container, false);
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
        titleList.add("安全监测");
        titleList.add("水文监测");
        titleList.add("束管监测");
        titleList.add("从业人员");
        fragmentlist.add(HydrologyDetectionFragment.newInstance());
        fragmentlist.add(PeopleInJobFragment.newInstance());
        fragmentlist.add(SafetyDetectionFragment.newInstance());
        fragmentlist.add(ShuGuanDetectionFragment.newInstance());
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
                if (getActivity() instanceof comprehensiveDetectionFragmentListener) {
                    ((comprehensiveDetectionFragmentListener) getActivity()).comprehensiveDetectionFragment();
                }
                break;
            default:
                break;
        }
    }
    public interface comprehensiveDetectionFragmentListener {
        public void comprehensiveDetectionFragment();
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
