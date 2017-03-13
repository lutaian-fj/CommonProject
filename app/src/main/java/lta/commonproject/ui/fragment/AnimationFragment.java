package lta.commonproject.ui.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lta.commonproject.R;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2017/3/6
 */

public class AnimationFragment extends BaseFragment {
    @BindView(R.id.btn_translate)
    Button mTranslateBtn;
    @BindView(R.id.iv_translate)
    ImageView mTranslateIv;
    @BindView(R.id.btn_rotate)
    Button mRotateBtn;
    @BindView(R.id.iv_rotate)
    ImageView mRotateIv;

    @Override
    public int initResource() {
        return R.layout.fragment_animation;
    }

    @Override
    public void initComponent(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListener() {
        mTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                        .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                        0.9f);
                animation.setDuration(4500); //设置一次持续时间
                animation.setRepeatCount(-1); //设置重复次数
                animation.setRepeatMode(Animation.RESTART); //设置是否重新开始 Animation.RESTART-重新开始，Animation.REVERSE-反向执行
                mTranslateIv.startAnimation(animation);
            }
        });
        mRotateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*****************RotateAnimation**************************/
                RotateAnimation animation =new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,
                        0.2f,Animation.RELATIVE_TO_SELF,0.2f);
                animation.setDuration(500); //设置执行时间
                animation.setRepeatCount(-1); //设置重复次数
                animation.setFillAfter(false); //是否停留在执行结束的状态
                mRotateIv.startAnimation(animation);

                /*****************AlphaAnimation**************************/
//                AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
//                alphaAnimation.setDuration(1000);
//                mRotateIv.startAnimation(alphaAnimation);

                /*****************ScaleAnimation**************************/
//                ScaleAnimation animation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                animation.setDuration(2000);//设置动画持续时间
//                animation.setRepeatCount(-1);//设置重复次数
//                mRotateIv.startAnimation(animation);
            }
        });
    }
}
