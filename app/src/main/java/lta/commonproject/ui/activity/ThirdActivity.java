package lta.commonproject.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lta.commonproject.R;
import lta.commonproject.ui.view.MyView;

public class ThirdActivity extends AppCompatActivity {

    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        myView = (MyView) findViewById(R.id.myView);
//        myView.setText("你说有没有问题3");
        String time = "2016年1月6日12时30分50秒";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        try {
            Date date = sdf.parse(time);
            long timeMap = date.getTime();
            Date mDate = new Date(timeMap) ;
            myView.setText(timeMap+"");
            Log.e("lta","mData="+mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
