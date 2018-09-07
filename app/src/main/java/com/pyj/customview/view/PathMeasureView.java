package com.pyj.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathMeasureView extends View {
    private int which = 0;

    private int mWidth, mHeight;

    private Paint mPaint;

    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
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

        switch (which) {
            case 0:
                pathMeasureForceClosedTrue(canvas);
                break;

            case 1:
                pathMeasureForceClosedFalse(canvas);
                break;

            case 2:
                getSegmentStartWithMoveToTrue(canvas);
                break;

            case 3:
                getSegmentStartWithMoveToFalse(canvas);
                break;

            case 4:
                nextContour(canvas);
                break;
        }
    }

    private void nextContour(Canvas canvas) {
        //1.曲线的顺序与 Path 中添加的顺序有关。
        //2.getLength 获取到到是当前一条曲线分长度，而不是整个 Path 的长度。
        //3.getLength 等方法是针对当前的曲线。

        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();

        path.addRect(-100, -100, 100, 100, Path.Direction.CW);  // 添加小矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);  // 添加大矩形

        canvas.drawPath(path, mPaint);

        PathMeasure measure = new PathMeasure(path, false);     // 将Path与PathMeasure关联

        int len1 = (int) measure.getLength();

        measure.nextContour();                                  // 跳转到下一条路径

        int len2 = (int) measure.getLength();

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30f);
        canvas.drawText("长度为:" + len1, -100, 150, mPaint);
        canvas.drawText("长度为:" + len2, -100, 250, mPaint);
    }

    private void getSegmentStartWithMoveToTrue(Canvas canvas) {
        //如果 startWithMoveTo 为 true(保证截取得到的 Path 片段不会发生形变)
        //则被截取出来到Path片段保持原状

        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        //path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.addCircle(0,0,300, Path.Direction.CW);

        Path dst = new Path();

        PathMeasure pathMeasure = new PathMeasure(path, false);

        //pathMeasure.getSegment(200, 600, dst, true);
        pathMeasure.getSegment(100, 300, dst, true);

        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawPath(dst, mPaint);
    }

    private void getSegmentStartWithMoveToFalse(Canvas canvas) {
        //如果 startWithMoveTo 为 false(保证存储截取片段的 Path(dst) 的连续性)
        //则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，
        // 以保证 dst 的连续性。

        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
       // path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.addCircle(0,0,300, Path.Direction.CW);

        Path dst = new Path();

        PathMeasure pathMeasure = new PathMeasure(path, false);

        //pathMeasure.getSegment(200, 600, dst, false);
        pathMeasure.getSegment(100, 300, dst, false);

        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawPath(dst, mPaint);
    }

    private void pathMeasureForceClosedTrue(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();

        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure pathMeasure = new PathMeasure(path, true);

        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30f);
        canvas.drawText("长度为:" + (int) pathMeasure.getLength(), 0, 10, mPaint);
    }

    private void pathMeasureForceClosedFalse(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();

        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure pathMeasure = new PathMeasure(path, false);

        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30f);
        canvas.drawText("长度为:" + (int) pathMeasure.getLength(), 0, 10, mPaint);
    }


    public void setShowWhich(int which) {
        this.which = which;
        postInvalidate();
    }
}
