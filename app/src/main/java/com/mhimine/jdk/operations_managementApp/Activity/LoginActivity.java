package com.mhimine.jdk.operations_managementApp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mhimine.jdk.operations_managementApp.Fragment.LoginDailogFragment;
import com.mhimine.jdk.operations_managementApp.R;
import com.mhimine.jdk.operations_managementApp.Utils.Utils;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {




    static LoginDailogFragment loginDailogFragment;
    String namespace = "http://tempuri.org/";
    //String Url = "http://47.92.68.57:8102/WebServices_Device_Management.asmx?WSDL";
    String Url = "http://47.92.68.57:8102/WebService_MySql_Eq_Management.asmx?WSDL";
    String methodName = "Check_User";
    public View v;
    MyListener myListener;
    private Boolean password_display = false;
    @Bind(R.id.et_password)
    EditText editTextPassword;
    @Bind(R.id.et_name)
    EditText editTextName;
    @Bind(R.id.btn_login)
    Button btn_Login;
    @Bind({R.id.iv_swich_passwrod})
    ImageView iv_Switch_password;
    @Bind(R.id.delete_username)
    ImageView iv_delete_username;
    //    @Bind(R.id.btn_register)
//    TextView txt_appName;
    public final static int TASK_LOOP_COMPLETE = 0;
    private boolean isLogin;
    String name;
    String password;
    Map<String, Object> params = new HashMap<>();
    SharedPreferences sharedPreferences;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            Bundle bundle = new Bundle();
            LoginActivity.this.finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            bundle.putInt("login",2);
            bundle.putString("username",name);
            i.putExtra("LoginInfo",bundle);
            LoginActivity.this.finish();
            startActivity(i);
        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            btn_Login.setOnClickListener(this);
            iv_Switch_password.setOnClickListener(this);
            iv_delete_username.setOnClickListener(this);
            iv_delete_username.setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                name = editTextName.getText().toString();
                password = editTextPassword.getText().toString();


                pd = ProgressDialog.show(LoginActivity.this, "登录", "正在登录…");
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入用户名跟密码", Toast.LENGTH_SHORT).show();
                    messageListener.sendEmptyMessage(TASK_LOOP_COMPLETE);
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //sleep(5000);
                            int data = Login(name, password);
                            if (data == 1) {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, "请输入正确的用户名跟密码", Toast.LENGTH_SHORT).show();
                                messageListener.sendEmptyMessage(TASK_LOOP_COMPLETE);
                                Looper.loop();
                            } else if (data == 0) {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
                                messageListener.sendEmptyMessage(TASK_LOOP_COMPLETE);
                                Looper.loop();
                            } else {
                              // myListener.sendContent(String.valueOf(data), name);//将内容进行回传

                                //步骤2： 实例化SharedPreferences.Editor对象
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                //步骤3：将获取过来的值放入文件
                                editor.putBoolean("isLogin", true);
                                editor.putString("username", name);
                                //步骤4：提交
                                editor.apply();
                                Bundle bundle = new Bundle();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                bundle.putInt("login",data);
                                bundle.putString("username",name);
                                i.putExtra("LoginInfo",bundle);
                                LoginActivity.this.finish();
                                startActivity(i);
                              //  messageListener.sendEmptyMessage(TASK_LOOP_COMPLETE);
                            }
                        }
                    }).start();
                }

                break;
            case R.id.iv_swich_passwrod:
                //密码可见
                if (password_display == false) {
                    iv_Switch_password.setImageResource(R.mipmap.show_psw);
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password_display = true;
                } else {
                    //密码不可见
                    iv_Switch_password.setImageResource(R.mipmap.show_psw_press);
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password_display = false;
                }
                break;
            case R.id.delete_username:
                editTextName.setText("");
                break;
        }

    }

    private Handler messageListener = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case TASK_LOOP_COMPLETE:
                    pd.dismiss();
                    break;

            }
        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        if(pd.isShowing()){
            pd.dismiss();
        }
    }
    private int Login(String name, String password) {
        int isLogin = 0;
        params.put("username", name);
        params.put("password", password);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SoapObject soapObject = Utils.callWS(namespace, methodName,
                Url, params);
        if (soapObject != null) {
            String detail = soapObject.getProperty("Check_UserResult").toString();
            if (Integer.parseInt(detail) == 0)
                return 1;
            else
                return 2;
        } else {
            System.out.println("This is null...");
        }
        return isLogin;
    }

    public interface MyListener {
        void sendContent(String info, String username);
    }
}
