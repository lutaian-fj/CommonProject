package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

import lta.commonproject.R;
import lta.commonproject.ui.widget.CustomLayout;

public class SecondActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    //    @Bind(R.id.btn_first)
    public Button mFirstBtn;
    //    @Bind(R.id.btn_second)
    public Button mSecondBtn;
    //    @Bind(R.id.imageView)
    ImageView imageView;
    private FloatingActionButton mFab;

    private LinearLayout mCustonLayout;

    private CustomLayout mCustom;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
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


        /**************************Glide显示图片****************************************************/
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(mContext)
                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJ1bKxzGISBR1AALeECdcYq4AALHwQJMUXsAAt4o510.jpg")
                .into(imageView);

        /**************************fab****************************************************/
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "点击了FAB", Snackbar.LENGTH_LONG)
                        .setAction("Close", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SecondActivity.this,"first floatingActionButton",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
//                Toast.makeText(SecondActivity.this, "first floatingActionButton", Toast.LENGTH_SHORT).show();

            }
        });

        /**************************自定义LinearLayout****************************************************/
//        mCustonLayout = (LinearLayout) findViewById(R.id.layout_custom);
//        CustomLayout customLayout1 = new CustomLayout(this);
//        customLayout1.setConfirmBtnClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"第一个按钮的确定",Toast.LENGTH_SHORT).show();
//            }
//        });
//        customLayout1.setCancleBtnClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"第一个按钮的取消",Toast.LENGTH_SHORT).show();
//            }
//        });
//        mCustonLayout.addView(customLayout1);

        mCustom = (CustomLayout) findViewById(R.id.custom);
        mCustom.setConfirmBtnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"第一个按钮的确定",Toast.LENGTH_SHORT).show();
            }
        });
        mCustom.setCancleBtnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"第一个按钮的取消",Toast.LENGTH_SHORT).show();
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
//            Observable.create(new Observable.OnSubscribe<String>() {
//                @Override
//                public void call(Subscriber<? super String> subscriber) {
//                    Log.e("lta", "应该是子线程" + Thread.currentThread().getName());
//                    subscriber.onNext("hahah");
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
////                            new Action1<String>() {
////                                @Override
////                                public void call(String s) {
////
////                                }
////                            }
//                            new Subscriber<String>() {
//                                @Override
//                                public void onCompleted() {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//
//                                }
//
//                                @Override
//                                public void onNext(String s) {
//                                    Log.e("lta","应该是主线程"+Thread.currentThread().getName());
//                                }
//                            }
//                    );


            /********************************fab显示与隐藏**********************************************/
            if (mFab.isShown()) {
                mFab.hide();
            } else {
                mFab.show();
            }

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
