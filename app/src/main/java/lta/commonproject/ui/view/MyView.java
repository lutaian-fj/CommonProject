package lta.commonproject.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/7/11
 */
public class MyView extends TextView {
    private Paint mPaint;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(300,300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
//        mPaint.setColor(Color.BLUE);
//        mPaint.setTextSize(20);
//        String text = "Hello View";
//        canvas.drawText(text, 0, getHeight() / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
