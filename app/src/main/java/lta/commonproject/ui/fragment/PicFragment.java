package lta.commonproject.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import lta.commonproject.R;
import lta.commonproject.utils.CommonUtil;

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
        String path = "http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"Tencent/QQ_Images/1.jpg";
        Log.e("lta","Glide path is " + path);
        Glide.with(getActivity())
                .load(path)
                .into(mGlideIv);
//        Picasso.with(getActivity())
//                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg")
//                .into(mPicassoIv);
        AnimationDrawable animationDrawable = CommonUtil.getAnimationDrawable(getActivity());
        mPicassoIv.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
    }

    @Override
    public void addListener() {

    }
}
