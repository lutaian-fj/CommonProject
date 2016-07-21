package lta.commonproject.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import lta.commonproject.R;
import lta.commonproject.data.entity.FirstEventbusEntity;
import lta.commonproject.data.entity.SecondEventbusEntity;
import lta.commonproject.ui.view.MyView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mButton;
    private Button mStartThread;
    private ImageView mImageView;
    private MyView myView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mButton = (Button) findViewById(R.id.btn_jump);
        mStartThread = (Button) findViewById(R.id.btn_new_thread);
        mImageView = (ImageView) findViewById(R.id.iv_photo);
        myView = (MyView) findViewById(R.id.myView);
        myView.setText("测试自定义View");
    }

    @Override
    protected void initData() {
        super.initData();
//        mImageView.setImageBitmap(BitmapHelper.getImage(getResources(), R.mipmap.picture));


//        URL url = null;
//        try {
//            url = new URL("http://www.2345.com/girl/mm/mnjx9.jpg");
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
//            mImageView.setImageBitmap(bitmap);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            Bitmap bitmap = BitmapHelper.getBitmap("http://www.2345.com/girl/mm/mnjx9.jpg");
//            mImageView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);  // 注册Eventbus
        mButton.setOnClickListener(this);
        mStartThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(this, AppService.class);
//                startService(intent);
                break;
            case R.id.btn_new_thread:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        message.obj = "异步线程回来的数据";
//                        handler.sendMessage(message);
//                    }
//                }).start();
                MyThread thread = new MyThread();
                thread.start();

                break;
            default:
                break;
        }

    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Looper curentLooper = Looper.myLooper();
            Looper mainLooper = Looper.getMainLooper();
            String msg;
            if (curentLooper == null) {
                handler = new MyHandler(MainActivity.this, mainLooper);
                msg = "curLooper is null";
            } else {
                handler = new MyHandler(MainActivity.this, curentLooper);
                msg = "This is curLooper";
            }
            handler.removeMessages(0);
            Message m = handler.obtainMessage(1, 1, 1, msg);
            handler.sendMessage(m);
            m.sendToTarget();


        }
    }

    private MyHandler handler = null;

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity, Looper looper) {
            super(looper);
            this.mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity weakActivity = mActivity.get();
            if(!weakActivity.isFinishing()) {
                if (weakActivity != null) {
                    weakActivity.toast(msg.obj.toString());
                }
            }

        }
    }

    /**
     * @Title:
     * @Description: Eventbus接收器
     * @param:
     * @return:
     * @throws:
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventbusSubscribe(Object object) {
        if (object instanceof FirstEventbusEntity) {
            Log.e("lta", ((FirstEventbusEntity) object).getTitle());
        } else if (object instanceof SecondEventbusEntity) {
            Log.e("lta", ((SecondEventbusEntity) object).getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 销毁Eventbus
        handler.removeCallbacksAndMessages(null);
    }

}
