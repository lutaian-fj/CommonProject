package lta.commonproject.ui.fragment;

import android.view.View;
import android.widget.TextView;

import lta.commonproject.R;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/10/9
 */

public class CustomViewPagerFragment extends BaseFragment {
    private TextView mTextView;
    private String mTitle;
    @Override
    public int initResource() {
        return R.layout.fragment_custom_view_pager;
    }

    @Override
    public void initComponent(View view) {
        mTextView = (TextView) view.findViewById(R.id.tv_fragment_content);

    }

    @Override
    public void initData() {
        if(mTextView != null) {
            mTextView.setText(mTitle);
        }
    }

    @Override
    public void addListener() {

    }
    public void setData(String title) {
        this.mTitle = title;
        initData();
    }
}
