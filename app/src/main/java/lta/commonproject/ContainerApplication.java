package lta.commonproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import lta.commonproject.utils.CrashHandler;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/7/31
 */
public class ContainerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
