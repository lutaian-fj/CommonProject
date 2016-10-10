package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;
import lta.commonproject.ui.adapter.CustomViewPagerAdapter;

public class ViewPagerActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private CustomViewPagerAdapter mAdapter;
    private List<String> data = new ArrayList<>();
    private Button mLeftBtn;
    private Button mRightBtn;
    private int mCurrentItem = 0;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mLeftBtn = (Button) findViewById(R.id.btn_left);
        mRightBtn = (Button) findViewById(R.id.btn_right);
    }

    @Override
    protected void initData() {
        data.add("广州");
        data.add("厦门");
        data.add("福州");
        data.add("北京");

        mAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_left:
                if (mCurrentItem > 0)
                    mViewPager.setCurrentItem(mCurrentItem - 1);
                break;
            case R.id.btn_right:
                if (mCurrentItem < data.size() - 1)
                    mViewPager.setCurrentItem(mCurrentItem + 1);
                break;
            default:
                break;
        }

    }
}
