package com.pyj.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomViewOfBessel extends View {
    private Paint mPaint;

    private int centerX, centerY;

    private int which = 0;

    private PointF start, end, control,control1, control2;

    private boolean isTwoBessel=true;

    public void setTwoBessel(boolean twoBessel) {
        isTwoBessel = twoBessel;
    }

    private boolean mode = true;

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public CustomViewOfBessel(Context context) {
        super(context);
    }

    public CustomViewOfBessel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);

        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        // 初始化数据点和控制点的位置
        start.x = centerX-200;
        start.y = centerY;
        end.x = centerX+200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY-100;

        control1.x = centerX-200;
        control1.y = centerY - 100;
        control2.x = centerX+200;
        control2.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘

        if(isTwoBessel){
            control.x = event.getX();
            control.y = event.getY();
        }else{
            if (mode) {
                control1.x = event.getX();
                control1.y = event.getY();
            } else {
                control2.x = event.getX();
                control2.y = event.getY();
            }
        }

        postInvalidate();
        //invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (which) {
            case 0:
                quadTo(canvas);
                break;

            case 1:
                cubicTo(canvas);
                break;

            case 2:
                //setLastPoint(canvas);
                break;

            case 3:
                //close(canvas);
                break;

            case 4:
                //addRect(canvas);
                break;

            case 5:
                //addPath(canvas);
                break;

            case 6:
                //addArc(canvas);
                break;

            case 7:
                //arcTo(canvas);
                break;
        }
    }

    private void quadTo(Canvas canvas){
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(path, mPaint);
    }

    private void cubicTo(Canvas canvas){
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y,control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y,end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }

    public void setShowWhich(int which) {
        this.which = which;
        postInvalidate();
        //invalidate();
    }
}
