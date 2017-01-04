package lta.commonproject.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import lta.commonproject.R;
import lta.commonproject.ui.activity.LineChartActivity;
import lta.commonproject.ui.activity.PieChartActivity;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/9/8
 */
public class MPChartFragment extends BaseFragment implements View.OnClickListener{

    private Context mContext; // 上下文
    private Button mLineChartBtn;  // 线性图标Btn


    @Override
    public int initResource() {
        return R.layout.fragment_mp_chart;
    }

    @Override
    public void initComponent(View view) {
          mLineChartBtn = (Button) view.findViewById(R.id.btn_line_chart);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListener() {
        mLineChartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_line_chart:
                LineChartActivity.launch(mContext,LineChartActivity.class);
                break;
            case R.id.btn_pie_chart:
                PieChartActivity.launch(mContext,PieChartActivity.class);
                break;
            default:
                break;
        }
    }
}
