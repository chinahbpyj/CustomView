package com.pyj.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pyj.customview.bean.PieChartData;

import java.util.ArrayList;

//饼状图
public class PieChartView extends View {
    private ArrayList<PieChartData> dataSource = new ArrayList<>();

    private Paint mPaint = new Paint();

    private float startAngle;
    private float sweepAngle = 60f;

    private int mWidth;
    private int mHeight;

    private int[] colors = {0xFFCCFF00,
            0xFF6495ED,
            0xFFE32636,
            0xFF800000,
            0xFF808000,
            0xFFFF8C69,
            0xFF808080,
            0xFFE6B800,
            0xFF7CFC00};

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dataSource == null || dataSource.size() == 0) {
            return;
        }

        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.5);  // 饼状图半径
        RectF rectF = new RectF(-r, -r, r, r);

        for (int i = 0; i < dataSource.size(); i++) {
            mPaint.setColor(colors[i]);
            PieChartData pieChartData = dataSource.get(i);
            sweepAngle = pieChartData.getValue();
            canvas.drawArc(rectF, startAngle, sweepAngle, true, mPaint);
            startAngle += pieChartData.getValue();
        }
    }

    public void setDataSource(ArrayList<PieChartData> dataSource) {
        this.dataSource = dataSource;
        invalidate();
    }
}
