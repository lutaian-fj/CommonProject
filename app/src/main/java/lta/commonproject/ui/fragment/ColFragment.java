package lta.commonproject.ui.fragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import lta.commonproject.R;

/**
 * @author: LuTaiAn
 * @ClassName: ColFragment
 * @Description: CoordinatorLayout
 * @date: 2017/1/21
 */

public class ColFragment extends BaseFragment {
    private Button mButton;
    @Override
    public int initResource() {
        return R.layout.fragment_col;
    }

    @Override
    public void initComponent(View view) {
        mButton = (Button) view.findViewById(R.id.btn);
    }

    @Override
    public void initData() {
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX()-v.getWidth()/2);
                        v.setY(event.getRawY()-v.getHeight()/2);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void addListener() {

    }
}
