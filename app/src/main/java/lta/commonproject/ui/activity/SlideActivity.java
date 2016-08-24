package lta.commonproject.ui.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;
import lta.commonproject.ui.adapter.SlidePagerAdapter;
import lta.commonproject.ui.widget.SlideItem;

public class SlideActivity extends BaseActivity {
    private ViewPager mViewPager;
    private SlidePagerAdapter mAdapter;
    private List<View> views = new ArrayList<>();
    private Context mContext;
    @Override
    protected void initView() {
        mContext = this;
        mViewPager = (ViewPager) findViewById(R.id.vp_slide);
    }

    @Override
    protected void initData() {
        SlideItem slideItem1 = new SlideItem(mContext);
        slideItem1.setImageView(R.mipmap.splash1);
        views.add(slideItem1);

        SlideItem slideItem2 = new SlideItem(mContext);
        slideItem2.setImageView(R.mipmap.splash2);
        views.add(slideItem2);

        SlideItem slideItem3 = new SlideItem(mContext);
        slideItem3.setImageView(R.mipmap.splash3);
        views.add(slideItem3);

        mAdapter = new SlidePagerAdapter(mContext,views);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_slide;
    }

    @Override
    protected void initEvent() {
    }
}
