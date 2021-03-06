package com.mhimine.jdk.operations_managementApp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mhimine.jdk.operations_managementApp.Activity.DeviceDetailsActivity;
import com.mhimine.jdk.operations_managementApp.Activity.ScannerActivity;
import com.mhimine.jdk.operations_managementApp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WatchFragment extends Fragment {
    @Bind(R.id.myViewPager)
    ViewPager myViewPager;
    @Bind(R.id.watch_tl)
    TabLayout tabLayout;
    @Bind(R.id.no_check_txtview)
    TextView noCheckText;
    @Bind(R.id.do_check_txtview)
    TextView doCheckText;
    int doText;
    int noText;
    String detailDo;
    String detailNo;
    Fragment singleFragment;
    Fragment multiFragment;
    List<Fragment>   fragmentList = new ArrayList<Fragment>();
    private List<String> mTitleList = new ArrayList<>(4);
    View v;
    static WatchFragment watchFragment;
    String namespace = "http://tempuri.org/";
    String Url = "http://47.92.68.57:8102/WebService_MySql_Eq_Management.asmx?WSDL";
    String SelectDoCheckNumber = "SelectDoCheckNumber";
    String SelectNoCheckNumber = "SelectNoCheckNumber";
    private boolean isGetData = false;

    public static WatchFragment getInstance() {
        if (watchFragment == null) {
            watchFragment = new WatchFragment();
        }
        return watchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (v != null) {
//            ButterKnife.bind(this, v);
//            return v;
//        }

        v = inflater.inflate(R.layout.paf_fragment_layout, container, false);
        ButterKnife.bind(this, v);
        InitVariable();
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
        myViewPager.setAdapter(myAdapter);
        myViewPager.setOffscreenPageLimit(4);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(myViewPager);
        Button button = (Button) v.findViewById(R.id.bt_scanner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customScan();
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (android.os.Build.VERSION.SDK_INT > 9) {
//                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(policy);
//                }
//                SoapObject soapObjectSelectDoCheckNumber = Utils.callWS(namespace, SelectDoCheckNumber,
//                        Url, null);
//                SoapObject soapObjectSelectNoCheckNumber = Utils.callWS(namespace, SelectNoCheckNumber,
//                        Url, null);
//                if (soapObjectSelectDoCheckNumber != null && soapObjectSelectNoCheckNumber != null) {
//
//                    detailDo = soapObjectSelectDoCheckNumber.getProperty("SelectDoCheckNumberResult").toString();
//                    detailNo = soapObjectSelectNoCheckNumber.getProperty("SelectNoCheckNumberResult").toString();
//
//
//                } else {
//                    System.out.println("This is null...");
//                    detailDo = "0";
//                    detailNo = "0";
//                }
//                doCheckText.setText(detailDo);
//                noCheckText.setText(detailNo);
//            }
//        }).start();


        return v;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            fragmentList.clear();

            // if (fragmentList.contains( SingleFragment.newInstance(getActivity()))||fragmentList.contains(MultiFragment.newInstance(getActivity()))){
            fragmentList.remove(SingleFragment.newInstance(getActivity()));
            fragmentList.remove(MultiFragment.newInstance(getActivity()));
            //  }
            fragmentList.add(SingleFragment.newInstance(getActivity()));
            fragmentList.add(MultiFragment.newInstance(getActivity()));
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getActivity(), "内容为空", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "扫描成功", Toast.LENGTH_LONG).show();
                String equip_code = intentResult.getContents();
                Bundle bundle = new Bundle();
                bundle.putString("equip_code", equip_code);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), DeviceDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), equip_code, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void InitVariable() {
        mTitleList.add("未巡检设备");
        mTitleList.add("已巡检设备");
//        singleFragment = SingleFragment.newInstance(getActivity());
//        multiFragment = MultiFragment.newInstance(getActivity());

        fragmentList.clear();

        // if (fragmentList.contains( SingleFragment.newInstance(getActivity()))||fragmentList.contains(MultiFragment.newInstance(getActivity()))){
        fragmentList.remove(SingleFragment.newInstance(getActivity()));
        fragmentList.remove(MultiFragment.newInstance(getActivity()));
        //  }
        fragmentList.add(SingleFragment.newInstance(getActivity()));
        fragmentList.add(MultiFragment.newInstance(getActivity()));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {

        }

    }

    public void customScan() {
        //Activity中的初始化方式
//        new IntentIntegrator(this)
//                .setOrientationLocked(false)
//                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
//                .initiateScan(); // 初始化扫描
//        //Fragment中的初始化方式
        IntentIntegrator.forSupportFragment(this)
                .setOrientationLocked(false)
                .setCaptureActivity(ScannerActivity.class)
                .initiateScan();

    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;

    }
}
