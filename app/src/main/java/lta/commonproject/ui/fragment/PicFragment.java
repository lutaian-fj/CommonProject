package lta.commonproject.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import lta.commonproject.R;
import lta.commonproject.utils.CommonUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        Log.e("lta", "Glide path is " + path);
        Glide.with(getActivity())
                .load(path)
                .into(mGlideIv);
//        Picasso.with(getActivity())
//                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg")
//                .into(mPicassoIv);


        /*********************图片动态播放*******************************/
        AnimationDrawable animationDrawable = CommonUtil.getAnimationDrawable(getActivity());
        mPicassoIv.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();


        /***********************RxBinding的使用*************************/

        RxView.clicks(mGlideIv)
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        return "对图片进行点击，很有意思的哦";
                    }
                })
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String str) {
                        Log.e("lta", str);
                    }
                });

        Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("猜猜看这个是什么");
            }
        }).map(new Func1<Object, String>() {

            @Override
            public String call(Object s) {
                return "你妹的啊";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        Log.e("lta", o);

                    }
                }
        );
    }

    @Override
    public void addListener() {

    }
}
