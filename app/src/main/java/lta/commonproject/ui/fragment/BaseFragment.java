package lta.commonproject.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author LTA
 * @ClassName: BaseFragment
 * @Description: Fragment基类
 * @date 2016/3/17 15:44
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment_01";
    protected String mTAG;

    /**
     * 初始化布局资源文件
     */
    public abstract int initResource();

    /**
     * 初始化组件
     */
    public abstract void initComponent(View view);

    /**
     * 初始化数据,在此请求网络数据
     */
    public abstract void initData();

    /**
     * 添加监听
     */
    public abstract void addListener();

    /**
     *
     * @Title: onCreate
     * @Description: 创建初始化
     * @param
     * @return void    返回类型
     * @throws
     */
    public void onCreate(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mTAG = getClassName();
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate:" + this);
        onCreate();
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(initResource(), null);
        Log.e(TAG, "onCreateView:"+this);
        initComponent(view);
        Log.e(TAG, ""+this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addListener();
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * @Title:
     * @Description: 打印调试
     * @param:
     * @return:
     * @throws:
     */
    public void outPut(String dsc,String str) {
        Log.e("lta",dsc+":"+str);
    }

    /**
     * @Title:
     * @Description: 吐司调试
     * @param:
     * @return:
     * @throws:
     */
    public void toast(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}
