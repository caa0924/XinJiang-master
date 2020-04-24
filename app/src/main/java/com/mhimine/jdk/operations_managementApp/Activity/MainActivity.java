package com.mhimine.jdk.operations_managementApp.Activity;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


import com.mhimine.jdk.operations_managementApp.Fragment.A_Map.A_MapFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.AboutProjectFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.AuthorityManagementFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.CheckEquipFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.CheckManageFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.ComprehensiveDetection.ComprehensiveDetectionFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.DispatchingManagementFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.Fragment1;

import com.mhimine.jdk.operations_managementApp.Fragment.IndustrialVideo.IndustrialVideoFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.LoginDailogFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.OutOfDateFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.ProductionTechnologyFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.ProductionTechnology.child.PeopleFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.SecurityManagement.SecurityManagementFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.WatchAndShakeFragment;
import com.mhimine.jdk.operations_managementApp.Fragment.WorkAlertFragment;
import com.mhimine.jdk.operations_managementApp.Model.Activation;
import com.mhimine.jdk.operations_managementApp.R;
import com.mhimine.jdk.operations_managementApp.Utils.BackHandlerHelper;
import com.mhimine.jdk.operations_managementApp.Utils.SnackBarUtils;

import java.util.Stack;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by JDK on 2016/8/28.
 */
public class MainActivity extends BaseActivity implements WatchAndShakeFragment.watchAndShakeFragmentListener
        , AboutProjectFragment.aboutProjectDrawerIconListener,
        AuthorityManagementFragment.authorityManagementFragmentListener, Fragment1.fragment1Listener
        , LoginDailogFragment.MyListener, WorkAlertFragment.workAlertFragmentListener
        , CheckManageFragment.checkManageFragmentListener, WorkAlertFragment.FragmentListener
        , OutOfDateFragment.BackOutOfDateListenter, CheckEquipFragment.fragmentCheckEquipListener
        , DispatchingManagementFragment.dispatchingManagementFragmentListener
        , ComprehensiveDetectionFragment.comprehensiveDetectionFragmentListener
        , SecurityManagementFragment.securityManagementFragmentListener
        , A_MapFragment.a_mapFragmentListener, IndustrialVideoFragment.industrialVideoFragmentListener
        , ProductionTechnologyFragment.productionTechnologyFragmentListener
        , ProductionTechnologyFragment.goToFragment, PeopleFragment.BackProdutionTechnology {
    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    private boolean isLogin;
    private boolean isOpen;
    private WatchAndShakeFragment watchAndShakeFragment;
    private DispatchingManagementFragment dispatchingManagementFragment;
    private OutOfDateFragment outOfDateFragment;
    private CheckManageFragment checkManageFragment;
    private WorkAlertFragment workAlertFragment;
    private PeopleFragment peopleFragment;
    private IndustrialVideoFragment industrialVideoFragment;
    private ComprehensiveDetectionFragment comprehensiveDetectionFragment;
    private SecurityManagementFragment securityManagementFragment;
    private A_MapFragment a_mapFragment;
    private ProductionTechnologyFragment productionTechnologyFragment;
    private CheckEquipFragment checkEquipFragment;
    private Fragment1 fragment1;
    private AboutProjectFragment aboutProjectFragment;
    private AuthorityManagementFragment authorityManagementFragment;
    private Fragment currentFragment;
    private long lastBackKeyDownTick = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1500;
    private Activation activation;
    private String position;
    SharedPreferences sharedPreferences;
    public static Stack<Fragment> stack = new Stack<>();//在此处定义zhan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        Utils.init(this);
        createMainActivity();

    }

    private void createMainActivity() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ButterKnife.bind(this);

        showDefaultFragment();


        mNavigationView.setItemIconTintList(null);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        initNavigationViewItemSelected();

    }

    public void showDefaultFragment() {

//        Intent i = getIntent();
//        Bundle bundle = i.getBundleExtra("LoginInfo");
//        String username = bundle.getString("username");
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        String username1 = sharedPreferences.getString("username", "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username1);
        editor.apply();

        if (dispatchingManagementFragment == null) {
            dispatchingManagementFragment = DispatchingManagementFragment.newInstance();
        }

        addFragment(R.id.activity_main, dispatchingManagementFragment);
        currentFragment = dispatchingManagementFragment;


    }


    public void initNavigationViewItemSelected() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nacigation_menu_dispatching_management:
                        if (dispatchingManagementFragment == null) {
                            dispatchingManagementFragment = DispatchingManagementFragment.newInstance();
                        }
                        switchFragment(currentFragment, dispatchingManagementFragment);
                        break;
                    case R.id.navigation_menu_comprehensive_detection:
                        if (comprehensiveDetectionFragment == null) {
                            comprehensiveDetectionFragment = ComprehensiveDetectionFragment.newInstance();
                        }
                        switchFragment(currentFragment, comprehensiveDetectionFragment);
                        break;
                    case R.id.navigation_menu_production_technology:
                        if (productionTechnologyFragment == null) {
                            productionTechnologyFragment = productionTechnologyFragment.newInstance();
                        }
                        switchFragment(currentFragment, productionTechnologyFragment);
                        break;
                    case R.id.navigation_menu_safety_management:
                        if (securityManagementFragment == null) {
                            securityManagementFragment = SecurityManagementFragment.newInstance();
                        }
                        switchFragment(currentFragment, securityManagementFragment);
                        break;
                    case R.id.navigation_menu_industrial_video:
                        if (industrialVideoFragment == null) {
                            industrialVideoFragment = IndustrialVideoFragment.newInstance();
                        }
                        switchFragment(currentFragment, industrialVideoFragment);
                        break;
                    case R.id.navigation_menu_a_map:
                        if (a_mapFragment == null) {
                            a_mapFragment = A_MapFragment.newInstance();
                        }
                        switchFragment(currentFragment, a_mapFragment);
                        break;
                    case R.id.navigation_menu_about_app:
                        if (aboutProjectFragment == null) {
                            aboutProjectFragment = AboutProjectFragment.newInstance();
                        }
                        switchFragment(currentFragment, aboutProjectFragment);
                        break;
                    case R.id.navigation_cancel:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //步骤3：将获取过来的值放入文件
                        editor.putBoolean("isLogin", false);
                        editor.putString("username", null);

                        //步骤4：提交
                        editor.apply();
                        //MainActivity.this.finish();
                        //   watchAndShakeFragment = null;
                        dispatchingManagementFragment = null;
                        finish();
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);

                        startActivity(i);


//                        if (loginDailogFragment == null) {
//                            loginDailogFragment = LoginDailogFragment.newInstance();
//                        }
//                        switchFragment(currentFragment, loginDailogFragment);
                        break;

                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }

    public void switchFragment(Fragment from, Fragment to) {
        if (currentFragment != to) {
            currentFragment = to;
            if (!to.isAdded()) {
                getFragmentTransaction().hide(from).add(R.id.activity_main, to).commit();
            } else {
                getFragmentTransaction().hide(from).show(to).commit();
            }
        }
    }


    @Override
    public void watchAndShakeFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void workAlertFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {
        long currentTick = System.currentTimeMillis();
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (isOpen) {
                mDrawerLayout.closeDrawer(mNavigationView);
                isOpen = false;
            } else {

                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_app)
                        .setTitle("提示")
                        .setMessage("是否要退出本软件")

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                /**
                                 * super.onBackPressed ();不可以直接访问
                                 */
                            }
                        }).create();
                //最后别忘了写create创建
                alertDialog.show();
                /**
                 * 同时创建好也别忘了显示
                 */

            }
        }
    }

    @Override
    public void aboutProjectDrawerIcon() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public void quitOnClick(View v) {
//        finish();
//        System.exit(0);
        exitApp();
    }

    private void exitApp() {

        finish();
        System.exit(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void authorityManagementFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }

    }

    @Override
    public void fragment1() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void sendContent(String info, String username) {
        if (info != null && !"".equals(info)) {
            if (Integer.parseInt(info) == 2) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //步骤3：将获取过来的值放入文件
                editor.putString("username", username);
                editor.apply();
                if (watchAndShakeFragment == null) {
                    watchAndShakeFragment = WatchAndShakeFragment.newInstance();
                }
                switchFragment(currentFragment, watchAndShakeFragment);

            }
        }

    }

    @Override
    public void checkManageFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void sendInfo(String info) {
        if (info == "outofdate") {
            if (outOfDateFragment == null) {
                outOfDateFragment = OutOfDateFragment.newInstance();
            }

            switchFragment(currentFragment, outOfDateFragment);
        }
    }

    @Override
    public void BackOutOfDate() {
        if (workAlertFragment == null) {
            workAlertFragment = WorkAlertFragment.newInstance(this);
        }

        switchFragment(currentFragment, workAlertFragment);
    }

    @Override
    public void fragmentCheckEquip() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Store the game state
        //  outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }

    @Override
    public void dispatchingManagementFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void comprehensiveDetectionFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void securityManagementFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void a_mapFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void industrialVideoFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void productionTechnologyFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void GotoPeopleFragment() {
        if (peopleFragment == null) {
            peopleFragment = PeopleFragment.newInstance();
        }
        switchFragment(currentFragment, peopleFragment);
    }

    @Override
    public void backProduction() {
        if (productionTechnologyFragment == null) {
            productionTechnologyFragment = ProductionTechnologyFragment.newInstance();
        }
        switchFragment(currentFragment, productionTechnologyFragment);
    }
}
