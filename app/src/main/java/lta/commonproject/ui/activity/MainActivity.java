package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import lta.commonproject.R;
import lta.commonproject.data.entity.FirstEventbusEntity;
import lta.commonproject.data.entity.SecondEventbusEntity;
import lta.commonproject.ui.fragment.MPChartFragment;
import lta.commonproject.ui.fragment.PicFragment;
import lta.commonproject.ui.fragment.RecyclerViewFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    public static final String TAG = "MainActivity";
    private Context mContext;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private MenuItem mOldItem;
    private MenuItem mSearchMenu;
    private PicFragment mPicFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private MPChartFragment mMpChartFragment; // 图标的Fragment
    private RecyclerViewFragment mRecyleViewFragment;

    /**
     * @Title:
     * @Description: 入口方法
     * @param:
     * @return:
     * @throws:
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mContext = this;
        mToolbar = (Toolbar) this.findViewById(R.id.tb);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置主标题颜色
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            final ActionBar supportActionBar = getSupportActionBar();
            supportActionBar.setHomeButtonEnabled(true);
        }

        mToolbar.setOnMenuItemClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_left);
//        if (mNavigationView != null) {
//            mToolbar.setTitleTextColor(Color.WHITE);
//            final Drawable navigationIcon = mToolbar.getNavigationIcon();
//            if (navigationIcon != null) {
//                navigationIcon.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//                Log.e("lta", "******************************************");
//            }
//        }
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();

        if (mNavigationView != null) {
            mToolbar.setTitleTextColor(Color.WHITE);
            final Drawable navigationIcon = mToolbar.getNavigationIcon();
            if (navigationIcon != null) {
                navigationIcon.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                Log.e("lta", "******************************************");
            }
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mOldItem != null)
                    mOldItem.setChecked(false);
                switch (item.getItemId()) {
                    case R.id.menu_main_my_home:
                        showFragment(0);
                        break;
                    case R.id.menu_main_all_home:
                        showFragment(1);
                        break;
                    case R.id.menu_main_info:
                        break;
                    case R.id.menu_main_recyleView:
                        showFragment(2);
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
        showFragment(0);
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
                SecondActivity.launch(MainActivity.this);
                Toast.makeText(this, "查找按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
                Toast.makeText(this, "分享按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
//                Toast.makeText(this, "设置一", Toast.LENGTH_SHORT).show();
                ViewPagerActivity.launch(this);
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

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0: {
                if (mPicFragment == null) {
                    mPicFragment = new PicFragment();
                    ft.add(R.id.fragment_content, mPicFragment);
                } else {
                    ft.show(mPicFragment);
                }
                break;
            }
            case 1: {
                if(mMpChartFragment == null) {
                    mMpChartFragment = new MPChartFragment();
                    ft.add(R.id.fragment_content,mMpChartFragment);
                }else {
                    ft.show(mMpChartFragment);
                }
                break;
            }
            case 2:
                if(mRecyleViewFragment == null) {
                    mRecyleViewFragment = new RecyclerViewFragment();
                    ft.add(R.id.fragment_content,mRecyleViewFragment);
                }else {
                    ft.show(mRecyleViewFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }


    public void hideFragment(FragmentTransaction ft) {
        //如果不为空，就先隐藏起来
        if (mPicFragment != null) {
            ft.hide(mPicFragment);
        }
        if(mMpChartFragment != null) {
            ft.hide(mMpChartFragment);
        }
        if(mRecyleViewFragment != null) {
            ft.hide(mRecyleViewFragment);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        onDoubleBackEdit();
    }
}
