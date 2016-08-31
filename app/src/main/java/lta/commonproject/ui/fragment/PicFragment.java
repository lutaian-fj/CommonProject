package lta.commonproject.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import lta.commonproject.R;

/**
 * @author: Administrator
 * @ClassName:
 * @Description:
 * @date: 2016/8/31
 */
public class PicFragment extends BaseFragment {
    private ImageView mPicassoIv;
    private ImageView mGlideIv;
    @Override
    public int initResource() {
        return R.layout.fragment_pic;
    }

    @Override
    public void initComponent(View view) {
        mPicassoIv = (ImageView) view.findViewById(R.id.iv_picasso);
        mGlideIv = (ImageView) view.findViewById(R.id.iv_glide);
    }

    @Override
    public void initData() {
        Glide.with(getActivity())
                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg")
                .into(mGlideIv);
        Picasso.with(getActivity())
                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg")
                .into(mPicassoIv);
    }

    @Override
    public void addListener() {

    }
}
