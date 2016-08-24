package lta.commonproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import lta.commonproject.R;

/**
 * @author: lutaian
 * @ClassName:
 * @Description: 自定义LinearLayout
 * @date: 2016/8/22
 */
public class CustomLayout extends LinearLayout{

    private Button mConfirmBtn;
    private Button mCancleBtn;
    public CustomLayout(Context context) {
        super(context);
        initView(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * @Title:
     * @Description: 初始化布局
     * @param:
     * @return:
     * @throws:
     */
    private void initView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,this);
        mConfirmBtn = (Button) view.findViewById(R.id.btn_confirm);
        mCancleBtn = (Button) view.findViewById(R.id.btn_cancel);

    }

    /**
     * @Title:
     * @Description: 点击按钮回调
     * @param:
     * @return:
     * @throws:
     */
    public void setConfirmBtnClick(OnClickListener listener) {
        mConfirmBtn.setOnClickListener(listener);
    }

    /**
     * @Title:
     * @Description: 取消按钮回调
     * @param:
     * @return:
     * @throws:
     */
    public void setCancleBtnClick(OnClickListener listener) {
        mCancleBtn.setOnClickListener(listener);
    }
}
