package com.mhimine.jdk.operations_managementApp.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.mhimine.jdk.operations_managementApp.Model.DataBean;
import com.mhimine.jdk.operations_managementApp.R;

/**
 * Created by hbh on 2017/4/20.
 * 子布局ViewHolder
 */

public class ChildViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private TextView childLeftText;
    //private TextView childRightText;

    public ChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final DataBean dataBean, final int pos){

        childLeftText = (TextView) view.findViewById(R.id.child_left_text);
       // childRightText = (TextView) view.findViewById(R.id.child_right_text);
        childLeftText.setText(dataBean.getChildLeftTxt());
       // childRightText.setText(dataBean.getChildRightTxt());

    }
}
