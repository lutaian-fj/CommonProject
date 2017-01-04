package lta.commonproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import lta.commonproject.R;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/8/23
 */
public class SlideItem extends LinearLayout {
    private ImageView mImageView;
    private Context mContext;
    public SlideItem(Context context) {
        super(context);
        initView(context);
        mContext = context;
    }

    public SlideItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        mContext = context;
    }

    /**
     * @Title:
     * @Description: 初始化布局
     * @param:
     * @return:
     * @throws:
     */
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_layout,this);
        mImageView = (ImageView) view.findViewById(R.id.iv_slide);
    }

    /**
     * @Title:
     * @Description: 设置图片
     * @param:
     * @return:
     * @throws:
     */
    public void setImageView(int imageViewId) {
        Glide.with(mContext)
                .load(imageViewId)
                .into(mImageView);
    }

}
