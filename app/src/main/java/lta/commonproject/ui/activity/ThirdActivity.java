package lta.commonproject.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lta.commonproject.R;
import lta.commonproject.ui.view.MyView;

public class ThirdActivity extends AppCompatActivity {

    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        myView = (MyView) findViewById(R.id.myView);
        myView.setText("你说有没有问题3");
    }
}
