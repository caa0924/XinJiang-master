package com.mhimine.jdk.operations_managementApp.Adapter;

import com.mhimine.jdk.operations_managementApp.Model.DataBean;

/**
 * Created by hbh on 2017/4/20.
 * 父布局Item点击监听接口
 */

public interface ItemClickListener {
    /**
     * 展开子Item
     * @param bean
     */
    void onExpandChildren(DataBean bean);

    /**
     * 隐藏子Item
     * @param bean
     */
    void onHideChildren(DataBean bean);
}
