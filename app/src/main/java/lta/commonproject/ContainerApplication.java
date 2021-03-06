package lta.commonproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import lta.commonproject.data.config.ServerConfig;
import lta.commonproject.utils.CrashHandler;

/**
 * @author: LuTaiAn
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
        ServerConfig.init();
    }
}
