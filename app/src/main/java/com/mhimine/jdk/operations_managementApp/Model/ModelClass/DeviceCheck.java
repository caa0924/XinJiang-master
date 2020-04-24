package com.mhimine.jdk.operations_managementApp.Model.ModelClass;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;


public class DeviceCheck {
    private int id;
    private String device_number;
    private String check_time;
    private String check_cycle;

    public DeviceCheck(int id, String device_number, String check_time, String check_cycle) {
        this.id = id;
        this.check_cycle = check_cycle;
        this.check_time = check_time;
        this.device_number = device_number;
    }

    public String getDevice_number() {
        return device_number;
    }
    public String getCheck_time() {
        return check_time;
    }
    public String getCheck_cycle(){
        return check_cycle;
    }
    public int getId(){
        return id;
    }


}
