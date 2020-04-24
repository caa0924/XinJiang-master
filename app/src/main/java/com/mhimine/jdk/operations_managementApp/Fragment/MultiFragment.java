package com.mhimine.jdk.operations_managementApp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mhimine.jdk.operations_managementApp.Adapter.DeviceCheckAdapter;
import com.mhimine.jdk.operations_managementApp.Model.ModelClass.DeviceCheck;
import com.mhimine.jdk.operations_managementApp.R;
import com.mhimine.jdk.operations_managementApp.Utils.Utils;

import org.ksoap2.serialization.SoapObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by JDK on 2016/8/4.
 */
public class MultiFragment extends Fragment {

    private View view;

    private List<DeviceCheck> userList = new ArrayList<>();
    String namespace = "http://tempuri.org/";
    String Url = "http://47.92.68.57:8102/WebService_MySql_Eq_Management.asmx?WSDL";
    String methodName = "SelectNoCheck";
    private List<DeviceCheck> check_equip_info = new ArrayList<>();
    SharedPreferences sharedPreferences;
    Map<String, Object> params = new HashMap<>();
    String method_SelDeviceNumberByUser = "SelCheckTimeByDeviceNumber";
    String method_SelLastCheckTime = "SelLastCheckTime";

    public static MultiFragment newInstance(Context context) {
        MultiFragment multiFragment = new MultiFragment();
        return multiFragment;
    }

    ///获取用户登录名
    public String getUsaeName() {
        sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);
        return username;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        view = inflater.inflate(R.layout.fragment_watch_multi, container, false);
        String username = getUsaeName();
        params.put("check_user", username);
        SoapObject soapObject = Utils.callWS(namespace, method_SelDeviceNumberByUser,
                Url, params);
        if (soapObject != null) {

            String detail = soapObject.getProperty("SelCheckTimeByDeviceNumberResult").toString();

            try {
                //将JSON字符串转换为List的结构
                List<Map<String, Object>> list = Utils.convertJSON2List(
                        detail, "Result_List", new String[]{"device_number", "check_time", "check_cycle"});
                // initUser(list);
                Date now_Date = new Date(System.currentTimeMillis());//系统的当前时间
                final ListView listView = (ListView) view.findViewById(R.id.lv_doCheck);
                Calendar c = Calendar.getInstance();
                for (int i = 0; i < list.size(); i++) {
                    String device_number = list.get(i).get("device_number").toString();
                    Date check_time = StringToDate(list.get(i).get("check_time").toString());

                    long time_diff = now_Date.getTime() - check_time.getTime();//时间差
                    long days = time_diff / (1000 * 60 * 60 * 24);//时间差转换为天
                    String equip_cycle = list.get(i).get("check_cycle").toString();
                    if (days > Integer.parseInt(equip_cycle)) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("device_number", device_number);
                        SoapObject soapObject1 = Utils.callWS(namespace, method_SelLastCheckTime,
                                Url, params);
                        if (soapObject1 != null) {
                            String detail1 = soapObject1.getProperty("SelLastCheckTimeResult").toString();
                            List<Map<String, Object>> list1 = Utils.convertJSON2List(
                                    detail1, "Result_List", new String[]{"device_number", "max(check_time)"});
                            if (!list1.get(0).get("max(check_time)").toString().isEmpty()) {
                                Date check_last_time = StringToDate(list1.get(0).get("max(check_time)").toString());
                                c.setTime(check_last_time);
                                c.add(Calendar.HOUR_OF_DAY, +10);

                                Date new_check_last_time = c.getTime();
                                int compareTo = now_Date.compareTo(new_check_last_time);
                                if (compareTo == -1) {
                                    initCheckEquipInfo(list1);
                                }
                            }

                        }
                    }

                }
                DeviceCheckAdapter adapter = new DeviceCheckAdapter(getActivity(),
                        R.layout.user_item, check_equip_info);

                listView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("This is null...");
        }

        return view;
    }

    private void initUser(List<Map<String, Object>> list) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
//            DeviceCheck user = new DeviceCheck(R.mipmap.equipment, l.get("device_number").toString(), (DateTime) l.get("check_time"), l.get("check_number").toString());
//            userList.add(user);
        }

    }

    private void initCheckEquipInfo(List<Map<String, Object>> list) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            DeviceCheck user = new DeviceCheck(R.mipmap.equipment, l.get("device_number").toString(), l.get("max(check_time)").toString(), getUsaeName());
            check_equip_info.add(user);
        }
    }

    public Date StringToDate(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null; //初始化date
        try {

            date = simpleDateFormat.parse(time); //Mon Jan 14 00:00:00 CST 2013

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
