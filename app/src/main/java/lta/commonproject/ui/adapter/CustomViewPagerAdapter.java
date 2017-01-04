package lta.commonproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.ui.fragment.CustomViewPagerFragment;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/10/9
 */

public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> data = new ArrayList<>();
    private CustomViewPagerFragment mCurrentFragment;
    public CustomViewPagerAdapter(FragmentManager fm,List<String> data) {
        super(fm);
        if(data != null && data.size() >0) {
            this.data.addAll(data);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return new CustomViewPagerFragment();
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CustomViewPagerFragment fragment = (CustomViewPagerFragment) super.instantiateItem(container, position);
        fragment.setData(data.get(position));
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (CustomViewPagerFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    /**
     * @Title:
     * @Description: 在Activity中获取当前Fragment对象，并进行相关操作
     * @param:
     * @return:
     * @throws:
     */
    public CustomViewPagerFragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
