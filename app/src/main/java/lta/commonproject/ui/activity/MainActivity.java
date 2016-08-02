package lta.commonproject.ui.activity;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import lta.commonproject.R;
import lta.commonproject.data.entity.FirstEventbusEntity;
import lta.commonproject.data.entity.SecondEventbusEntity;

public class MainActivity extends BaseActivity implements View.OnClickListener ,Toolbar.OnMenuItemClickListener{
    public static final String TAG = "MainActivity";
    private Context mContext;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private MenuItem mOldItem;
    private MenuItem mSearchMenu;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mContext = this;
        View view = findViewById(R.id.first_include);
        TextView textView1 = (TextView) view.findViewById(R.id.textView);
        textView1.setText("星期五");
        View view2 = findViewById(R.id.second_include);
        TextView textView2 = (TextView) view2.findViewById(R.id.textView2);
        textView2.setText("可能会下雨");
        mToolbar = (Toolbar) this.findViewById(R.id.tb);
//        mToolbar.setTitle("第一个Toolbar"); // 设置主标题
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置主标题颜色
//        mToolbar.setTitleTextAppearance(); 设置字体大小
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.mipmap.ic_menu_white);  // 设置导航栏图标
//            mToolbar.setLogo(R.mipmap.ic_launcher); // 设置app logo
            mToolbar.inflateMenu(R.menu.menu_main_right); // 设置右上角的填充菜单（貌似没作用啊！！！！）

        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });
        mToolbar.setOnMenuItemClickListener(this);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
//            }
//        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_left);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mOldItem != null)
                    mOldItem.setChecked(false);
                switch (item.getItemId()) {
                    case R.id.menu_main_my_home:
                        Log.e(TAG, "---->>个人首页");
                        SecondActivity.launch(MainActivity.this);
                        break;
                    case R.id.menu_main_all_home:
                        Log.e(TAG, "---->>公共首页");
                        break;
                    case R.id.menu_main_info:
                        Log.e(TAG, "---->>个人信息");
                        break;
                    default:
                        break;
                }

                mOldItem = item;
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);  // 注册Eventbus

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "查找按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "分享按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "设置一", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings_2:
                Toast.makeText(this, "设置二", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_right, menu);  // 添加Toolbar右边菜单
        mSearchMenu = menu.findItem(R.id.action_search);   // 获取右边三个按钮对应的按钮，可用来控制隐藏情况
        return true;
    }

    /**
     * @Title:
     * @Description: Eventbus接收器
     * @param:
     * @return:
     * @throws:
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventbusSubscribe(Object object) {
        if (object instanceof FirstEventbusEntity) {
            Log.e("lta", ((FirstEventbusEntity) object).getTitle());
        } else if (object instanceof SecondEventbusEntity) {
            Log.e("lta", ((SecondEventbusEntity) object).getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 销毁Eventbus
    }

}
