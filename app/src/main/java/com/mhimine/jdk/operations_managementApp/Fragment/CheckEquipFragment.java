package com.mhimine.jdk.operations_managementApp.Fragment;

import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhimine.jdk.operations_managementApp.R;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckEquipFragment extends android.support.v4.app.Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static CheckEquipFragment fragment1;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.drawerIcon)
    TextView drawerIcon;
    @Bind(R.id.watch_tv)
    TextView watch_tv;
    List<View> viewList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    private View v;
    private List<Fragment> fragmentlist = new ArrayList<>(2);
    private MyFragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_fragment1, container, false);

        Fragment_1 fragment_1 = new Fragment_1();
        Fragment_2 fragment_2 = new Fragment_2();
        titleList.add("未巡检设备");
        titleList.add("已巡检设备");
        ButterKnife.bind(this, v);
        fragmentlist.add(fragment_1);
        fragmentlist.add(fragment_2);

        initWidgets();
        return v;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setReenterTransition(true);
    }



    private void initWidgets() {
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        // getFragmentManager();
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
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
            case 2:
                watch_tv.setSelected(false);
                viewpager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface fragmentCheckEquipListener {
        public void fragmentCheckEquip();
    }

    @Override
    public void onClick(View v) {
        if (getActivity() instanceof fragmentCheckEquipListener) {
            ((fragmentCheckEquipListener) getActivity()).fragmentCheckEquip();
        }

    }

    public static CheckEquipFragment newInstance() {
        if (fragment1 == null) {
            fragment1 = new CheckEquipFragment();
        }
        return fragment1;
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
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
            //  super.destroyItem(container, position, object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }
    }

}
