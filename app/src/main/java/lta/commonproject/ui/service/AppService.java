package lta.commonproject.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AppService extends Service {
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("lta","AppService is creare");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lta","AppService is destory");
    }
}
