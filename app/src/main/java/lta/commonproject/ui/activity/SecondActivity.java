package lta.commonproject.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import lta.commonproject.R;
import lta.commonproject.data.entity.FirstEventbusEntity;
import lta.commonproject.data.entity.SecondEventbusEntity;

public class SecondActivity extends BaseActivity implements View.OnClickListener{

    private Button mFirstBtn;
    private Button mSecondBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mFirstBtn = (Button) findViewById(R.id.btn_first);
        mSecondBtn = (Button) findViewById(R.id.btn_second);
        mFirstBtn.setOnClickListener(this);
        mSecondBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mFirstBtn) {
//            FirstEventbusEntity firstEventbusEntity = new FirstEventbusEntity();
//            firstEventbusEntity.setTitle("第一个Eventbus");
//            EventBus.getDefault().post(firstEventbusEntity); // 发送Eventbus
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        handler = new MyHandler(SecondActivity.this,Looper.getMainLooper());
                        Message message = handler.obtainMessage();
                        message.obj = "测试异步线程";
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }else if(view == mSecondBtn) {
            SecondEventbusEntity secondEventbusEntity = new SecondEventbusEntity();
            secondEventbusEntity.setMessage("第二个Eventbus");
            EventBus.getDefault().post(secondEventbusEntity);
        }
    }

    private MyHandler handler = null;

    private static class MyHandler extends Handler {
        private final WeakReference<SecondActivity> mActivity;

        public MyHandler(SecondActivity activity, Looper looper) {
            super(looper);
            this.mActivity = new WeakReference<SecondActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SecondActivity weakActivity = mActivity.get();
//            if(!weakActivity.isFinishing()) {
                if (weakActivity != null) {
                    weakActivity.toast(msg.obj.toString());
//                }
            }

        }
    }


}
