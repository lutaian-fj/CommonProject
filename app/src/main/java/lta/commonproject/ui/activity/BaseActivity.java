package lta.commonproject.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import lta.commonproject.common.ExitHandler;


/**
 * 基础activity 类
 * Created by Sam on 2015/7/2.
 */
public class BaseActivity extends AppCompatActivity {

    protected Application mApplication;
    protected ExitHandler mExitHandler;
    private final static int EXIT_TIME_DELAY = 1000;

    public static void launch(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext,cls);
        packageContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);

        if (getLayoutRes() > 0) {
            setContentView(getLayoutRes());
        }
        mApplication = getApplication();
        initView();
        initData();
        initEvent();
    }

    protected int getLayoutRes() {
        return -1;
    }

    /**
     * 初始化视图
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化事件
     */
    protected void initEvent() {

    }

    /**
     * 显示 toast
     *
     * @param resId 资源ID
     */
    protected void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 toast
     *
     * @param text str
     */
    protected void toast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    /**
     * @Title:  onDoubleBackEdit
     * @Description:  双击退出
     * @param:
     * @return:
     * @throws:
     */
    protected void onDoubleBackEdit() {
        if(mExitHandler == null) {
            mExitHandler = new ExitHandler();
        }
        if(mExitHandler.isBackClicked()) {
            exitApp();
        }else {
            mExitHandler.sendEmptyMessage(ExitHandler.EXIT_MSG_WHAT);
            mExitHandler.sendEmptyMessageDelayed(ExitHandler.EXIT_MSG_OUT_TIME_WHAT, EXIT_TIME_DELAY);
            toast("再次点击返回键退出");
        }
    }

    /**
     * @Title: exitApp
     * @Description: 退出应用
     * @param:
     * @return:
     * @throws:
     */
    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

}

