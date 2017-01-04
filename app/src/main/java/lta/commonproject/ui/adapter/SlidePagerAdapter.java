package lta.commonproject.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/8/23
 */
public class SlidePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<View> views = new ArrayList<>();
    public SlidePagerAdapter(Context context, List<View> views) {
        mContext = context;
        this.views.addAll(views);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
