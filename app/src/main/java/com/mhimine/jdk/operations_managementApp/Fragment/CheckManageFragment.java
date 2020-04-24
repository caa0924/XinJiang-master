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
public class CheckManageFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static CheckManageFragment checkManageFragment;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    @Bind(R.id.watch_tv)
    TextView watch_tv;
    List<String> titleList = new ArrayList<>();
    private View v;
    private List<Fragment> fragmentlist = new ArrayList<>(6);
    private MyNewFragmentPagerAdapter adapter;


    public static CheckManageFragment newInstance() {
        if (checkManageFragment == null) {
            checkManageFragment = new CheckManageFragment();
        }

        return checkManageFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_check_manage, container, false);
        ButterKnife.bind(this, v);
        // fragmentlist.add(pitCheckFragment);
        initFragmentList();

        initWidgets();
        return v;
    }
    private void initFragmentList() {
        titleList.clear();
        titleList.add("综合日报");
        titleList.add("销售日报");
        titleList.add("生产日报");
        titleList.add("掘进日报");
        titleList.add("排班系统");
        titleList.add("值班系统");
        fragmentlist.add(ComprehensiveDailyFragment.newInstance());
        fragmentlist.add(DutySystemFragment.newInstance());
        fragmentlist.add(ProductionDailyFragment.newInstance());
        fragmentlist.add(SalesDailyFragment.newInstance());
        fragmentlist.add(SchedulingSystemFragment.newInstance());
        fragmentlist.add(TunnelingDailyFragment.newInstance());
    }
    private void initWidgets() {
        adapter = new MyNewFragmentPagerAdapter(getChildFragmentManager());
        // getFragmentManager();
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewpager);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        watch_tv.setOnClickListener(this);
        watch_tv.setTypeface(typeface);
        //初始化显示位置
        watch_tv.setSelected(true);
        viewpager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                watch_tv.setSelected(false);
                viewpager.setCurrentItem(0);
                break;
            case 1:
                watch_tv.setSelected(false);
                viewpager.setCurrentItem(1);
                break;
//            case 2:
//                watch_tv.setSelected(false);
//                viewpager.setCurrentItem(2);
//                break;
//            case 3:
//                watch_tv.setSelected(false);
//                viewpager.setCurrentItem(3);
//                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface checkManageFragmentListener {
        public void checkManageFragment();
    }

    @Override
    public void onClick(View v) {
        if (getActivity() instanceof checkManageFragmentListener) {
            ((checkManageFragmentListener) getActivity()).checkManageFragment();
        }
    }

    public class MyNewFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyNewFragmentPagerAdapter(FragmentManager fm) {
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
