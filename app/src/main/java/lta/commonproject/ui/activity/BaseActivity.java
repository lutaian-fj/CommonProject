package lta.commonproject.ui.activity;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * 基础activity 类
 * Created by Sam on 2015/7/2.
 */
public class BaseActivity extends AppCompatActivity {

    protected Application mApplication;
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


}

