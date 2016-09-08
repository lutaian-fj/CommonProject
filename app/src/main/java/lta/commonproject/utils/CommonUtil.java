package lta.commonproject.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/9/1
 */
public class CommonUtil {
    /**播放动画
     * @param mContext
     * @return
     */
    public static AnimationDrawable getAnimationDrawable(Context mContext) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i = 1; i <= 3; i++) {
            // 根据资源名称和目录获取R.java中对应的资源ID
            int id = mContext.getResources().getIdentifier("splash" + i, "mipmap",
                    mContext.getPackageName());
            // 根据资源ID获取到Drawable对象
            Drawable drawable = mContext.getResources().getDrawable(id);
            // 将此帧添加到AnimationDrawable中
            animationDrawable.addFrame(drawable, 1000);
        }
        animationDrawable.setOneShot(false); // 设置是否可重复播放 false-可以
        return animationDrawable;
    }

}
