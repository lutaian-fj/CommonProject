package lta.commonproject.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * @author: LuTaiAn
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
        mPaint.setColor(Color.YELLOW);
        int sroke_width =  2;
        canvas.drawLine(0, 0, this.getWidth() - sroke_width, 0, mPaint);
        canvas.drawLine(0, 0, 0, this.getHeight() - sroke_width, mPaint);
        canvas.drawLine(this.getWidth() - sroke_width, 0, this.getWidth() - sroke_width, this.getHeight() - sroke_width, mPaint);
        canvas.drawLine(0, this.getHeight() - sroke_width, this.getWidth() - sroke_width, this.getHeight() - sroke_width, mPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
