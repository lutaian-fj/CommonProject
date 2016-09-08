package lta.commonproject.ui.fragment;

import android.view.View;

import com.github.mikephil.charting.charts.LineChart;

import lta.commonproject.R;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/9/8
 */
public class MPChartFragment extends BaseFragment {
    private LineChart mLineChart;
    @Override
    public int initResource() {
        return R.layout.fragment_mp_chart;
    }

    @Override
    public void initComponent(View view) {
        mLineChart = (LineChart) view.findViewById(R.id.lc_line_chart);

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListener() {

    }
}
