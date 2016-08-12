package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

import lta.commonproject.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SecondActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
//    @Bind(R.id.btn_first)
    public Button mFirstBtn;
//    @Bind(R.id.btn_second)
    public Button mSecondBtn;
//    @Bind(R.id.imageView)
    ImageView imageView;

    public static void launch(Context context) {
        Intent intent = new Intent(context,SecondActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mContext = this;
        mFirstBtn = (Button) findViewById(R.id.btn_first);
        mSecondBtn = (Button) findViewById(R.id.btn_second);
        mFirstBtn.setOnClickListener(this);
        mSecondBtn.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(mContext)
                .load("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg")
                .into(imageView);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Click", Snackbar.LENGTH_LONG)
//                        .setAction("Close", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(SecondActivity.this,"first floatingActionButton",Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
                Toast.makeText(SecondActivity.this,"first floatingActionButton",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mContext = this;
    }

    @Override
    public void onClick(View view) {
        if (view == mFirstBtn) {
//            FirstEventbusEntity firstEventbusEntity = new FirstEventbusEntity();
//            firstEventbusEntity.setTitle("第一个Eventbus");
//            EventBus.getDefault().post(firstEventbusEntity); // 发送Eventbus
            /**************************启动异步线程****************************************************/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        handler = new MyHandler(SecondActivity.this, Looper.getMainLooper());
                        Message message = handler.obtainMessage(); //从消息池中获取空消息对象，以节省资源
                        message.obj = "测试异步线程";
                        handler.sendMessage(message); //该方法会将Message中的Target设置为Handler
//                        message.sendToTarget();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
            /***************************数据库操作***************************************************/
//            DBHelper dbHelper = new DBHelper(mContext);
//            DBManager.initialize(mContext,dbHelper);
//            DBManager dbManager = DBManager.getInstance();
//            SQLiteDatabase database = dbManager.getWritableDB();
//            String sql = "insert into info(name,student_num) values('卢泰桉','890425')";
//            database.execSQL(sql);


        } else if (view == mSecondBtn) {
//            SecondEventbusEntity secondEventbusEntity = new SecondEventbusEntity();
//            secondEventbusEntity.setMessage("第二个Eventbus");
//            EventBus.getDefault().post(secondEventbusEntity);
            /********************************RxJava实现异步操作**********************************************/
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    Log.e("lta", "应该是子线程" + Thread.currentThread().getName());
                    subscriber.onNext("hahah");
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
//                            new Action1<String>() {
//                                @Override
//                                public void call(String s) {
//
//                                }
//                            }
                            new Subscriber<String>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(String s) {
                                    Log.e("lta","应该是主线程"+Thread.currentThread().getName());
                                }
                            }
                    );

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
            if (!weakActivity.isFinishing()) {
                if (weakActivity != null) {
                    weakActivity.toast(msg.obj.toString());
                }
            }

        }
    }

}
