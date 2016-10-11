package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView mTitleTv1,mTitleTv2,mTitleTv3,mTitleTv4;
    private int bmpW;// 动画图片宽度
    private ImageView imageView;// 动画图片
    private int offset = 0;// 动画图片偏移量
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
        mTitleTv1 = (TextView) findViewById(R.id.tv_title_01);
        mTitleTv2 = (TextView) findViewById(R.id.tv_title_02);
        mTitleTv3 = (TextView) findViewById(R.id.tv_title_03);
        mTitleTv4 = (TextView) findViewById(R.id.tv_title_04);
        initImageView();

    }

    @Override
    protected void initData() {
        data.add("广州");
        data.add("厦门");
        data.add("福州");
        data.add("北京");
        mTitleTv1.setText("广州");
        mTitleTv2.setText("厦门");
        mTitleTv3.setText("福州");
        mTitleTv4.setText("北京");

        mAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
                Animation animation = new TranslateAnimation(one*mCurrentItem, one*position, 0, 0);
                mCurrentItem = position;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(300);
                imageView.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // state = 0 没有变动
                // state = 1 正在滑动
                // state = 2 滑动结束

            }
        });

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mTitleTv1.setOnClickListener(this);
        mTitleTv2.setOnClickListener(this);
        mTitleTv3.setOnClickListener(this);
        mTitleTv4.setOnClickListener(this);

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
            case R.id.tv_title_01:
                mViewPager.setCurrentItem(0);
                mCurrentItem = 0;
                break;
            case R.id.tv_title_02:
                mViewPager.setCurrentItem(1);
                mCurrentItem = 1;
                break;
            case R.id.tv_title_03:
                mViewPager.setCurrentItem(2);
                mCurrentItem = 2;
                break;
            case R.id.tv_title_04:
                mViewPager.setCurrentItem(3);
                mCurrentItem = 3;
                break;
            default:
                break;
        }

    }


    private void initImageView() {
        imageView= (ImageView) findViewById(R.id.iv_slide);
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 4 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }
}
