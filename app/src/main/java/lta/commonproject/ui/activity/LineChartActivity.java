package lta.commonproject.ui.activity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import lta.commonproject.R;

public class LineChartActivity extends BaseActivity {
    private LineChart mLineChart;
    private LineData mLineData;
    private ArrayList<String> mXVals;
    private ArrayList<Entry> mYVals;
    private Random mRandom;
    private LineDataSet mSet;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_line_chart;
    }

    @Override
    protected void initView() {
        mLineChart = (LineChart) findViewById(R.id.lc_line_chart);
    }

    @Override
    protected void initData() {

        mXVals = new ArrayList<>();
        mYVals = new ArrayList<>();
        mRandom = new Random();
        /***************************对X轴进行设置**********************************/
        XAxis xAxis = mLineChart.getXAxis(); // 获取x轴对象
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 将x轴移到底部，默认在顶部
        xAxis.setSpaceBetweenLabels(1);   // 设置x轴的间隔


        for (int i = 0; i < 12; i++) {
            float profix = mRandom.nextFloat();
            mYVals.add(new Entry(profix, i));
            mXVals.add((i + 1) + "月");
        }
        mSet = new LineDataSet(mYVals, "公司年度利润");
        mSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mLineData = new LineData(mXVals, mSet);
        mLineChart.setData(mLineData);
        mLineChart.setDescription("公司年度利润..");
        mLineChart.animateY(3000);
        mLineChart.animateX(3000);
        mLineChart.setTouchEnabled(true); // 设置是否可点击
        mLineChart.setDragEnabled(true); // 设置是否可缩放、拖动
        mLineChart.setHighlightPerTapEnabled(true);
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // 设置点击的监听事件
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                toast(e.getXIndex()+":"+e.getVal());
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
