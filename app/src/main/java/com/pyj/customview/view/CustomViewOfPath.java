package com.pyj.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewOfPath extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private int which = 0;

    public CustomViewOfPath(Context context) {
        super(context);
    }

    public CustomViewOfPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
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
                lineTo(canvas);
                break;

            case 1:
                moveTo(canvas);
                break;

            case 2:
                setLastPoint(canvas);
                break;

            case 3:
                close(canvas);
                break;

            case 4:
                addRect(canvas);
                break;

            case 5:
                addPath(canvas);
                break;

            case 6:
                addArc(canvas);
                break;

            case 7:
                arcTo(canvas);
                break;
        }
    }

    private void lineTo(Canvas canvas) {
        Path mPath = new Path();
        mPath.lineTo(200, 200);

        mPath.lineTo(400, 0);

        canvas.drawPath(mPath, mPaint);
    }

    private void moveTo(Canvas canvas) {
       /* moveTo只改变下次操作的起点，
        在执行完第一次LineTo的时候，
        本来的默认点位置是A(200,200),
        但是moveTo将其改变成为了C(200,100),
        所以在第二次调用lineTo的时候就是连接C(200,100) 到 B(200,0) 之间的直线*/
        Path mPath = new Path();
        mPath.moveTo(mWidth / 2, mHeight / 2);

        mPath.lineTo(100, 200);

        canvas.drawPath(mPath, mPaint);
    }

    private void setLastPoint(Canvas canvas) {
       /* setLastPoint是重置上一次操作的最后一个点，
        在执行完第一次的lineTo的时候，
        最后一个点是A(200,200),而setLastPoint更改最后一个点为C(200,100),
        所以在实际执行的时候，第一次的lineTo就不是从原点O到A(200,200)的连线了，
        而变成了从原点O到C(200,100)之间的连线了。*/
        Path mPath = new Path();
        mPath.lineTo(200, 200);

        mPath.setLastPoint(200, 400);

        mPath.lineTo(400, 0);

        canvas.drawPath(mPath, mPaint);
    }

    private void close(Canvas canvas) {
        /*close方法用于连接当前最后一个点和最初的一个点(如果两个点不重合的话)，
         最终形成一个封闭的图形。*/
        Path mPath = new Path();
        mPath.moveTo(100, 100);

        mPath.lineTo(300, 300);

        mPath.lineTo(400, 100);

        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }

    private void addRect(Canvas canvas) {
        //顺时针(CW)，逆时针(CCW)

       /* // 圆形
        public void addCircle (float x,
        float y,
        float radius,
        Path.Direction dir)

        // 椭圆
        public void addOval (RectF oval,
        Path.Direction dir)

        // 矩形
        public void addRect (float left,
        float top,
        float right,
         float bottom,
         Path.Direction dir)

        public void addRect (RectF rect,
        Path.Direction dir)

       // 圆角矩形
        public void addRoundRect (RectF rect,
         float[] radii,
          Path.Direction dir)

        public void addRoundRect (RectF rect,
        float rx,
         float ry,
         Path.Direction dir)
        */

        canvas.translate(mWidth / 2, mHeight / 2);

        Path mPath = new Path();

        RectF rectF = new RectF(-100, -100, 100, 100);

        mPath.addRect(rectF, Path.Direction.CW);

        mPath.setLastPoint(-200, 200);

        canvas.drawPath(mPath, mPaint);
    }

    private void addPath(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        //canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        Path src = new Path();

        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        src.addCircle(0, 0, 50, Path.Direction.CW);

        //path.addPath(src);
        path.addPath(src, 0, 150);

        mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
        canvas.drawPath(path, mPaint);

    }

    private void addArc(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        //canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        path.lineTo(100, 100);

        RectF rectF = new RectF(0, 0, 200, 200);
        path.addArc(rectF, 0, 270f);

        canvas.drawPath(path, mPaint);
    }

    private void arcTo(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        //canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        //path.moveTo(100,100);
        path.lineTo(50, 50);

        RectF rectF = new RectF(0, 0, 200, 200);
        path.arcTo(rectF, 0, 270f);

        canvas.drawPath(path, mPaint);
    }


    public void setShowWhich(int which) {
        this.which = which;
        invalidate();
    }
}
