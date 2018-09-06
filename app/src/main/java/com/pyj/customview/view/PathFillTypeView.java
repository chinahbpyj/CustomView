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

public class PathFillTypeView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private int which = 0;

    public PathFillTypeView(Context context) {
        super(context);
    }

    public PathFillTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);

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
                even_odd(canvas);
                break;

            case 1:
                inverse_even_odd(canvas);
                break;

            case 2:
                winding(canvas);
                break;

            case 3:
                DIFFERENCE(canvas);
                break;
            case 4:
                REVERSE_DIFFERENCE(canvas);
                break;
            case 5:
                INTERSECT(canvas);
                break;
            case 6:
                UNION(canvas);
                break;
            case 7:
                XOR(canvas);
                break;

            case 8:
                computeBounds(canvas);
                break;

            case 9:
                eightDiagrams(canvas);
                break;
        }
    }

    private void even_odd(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();

        path.setFillType(Path.FillType.EVEN_ODD); // 设置Path填充模式为 奇偶规则

        RectF rectF = new RectF(-200, -100, 200, 100);

        path.addRect(rectF, Path.Direction.CW);

        canvas.drawPath(path, mPaint);
    }

    private void inverse_even_odd(Canvas canvas) {

        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();

        path.setFillType(Path.FillType.INVERSE_EVEN_ODD); // 设置Path填充模式为 反奇偶规则

        path.addRect(-200, -100, 200, 100, Path.Direction.CW);

        canvas.drawPath(path, mPaint);
    }

    private void winding(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();

        path.setFillType(Path.FillType.WINDING);

        path.addRect(-100, 100, 100, 100, Path.Direction.CCW);

        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);

        canvas.drawPath(path, mPaint);
    }

    private void DIFFERENCE(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-50,0,60, Path.Direction.CW);
        path2.addCircle(50,0,60, Path.Direction.CW);

        pathOpResult.op(path1,path2, Path.Op.DIFFERENCE);

        canvas.drawPath(pathOpResult,mPaint);
    }

    private void REVERSE_DIFFERENCE(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-50,0,60, Path.Direction.CW);
        path2.addCircle(50,0,60, Path.Direction.CW);

        pathOpResult.op(path1,path2, Path.Op.REVERSE_DIFFERENCE);

        canvas.drawPath(pathOpResult,mPaint);
    }

    private void INTERSECT(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-50,0,60, Path.Direction.CW);
        path2.addCircle(50,0,60, Path.Direction.CW);

        pathOpResult.op(path1,path2, Path.Op.INTERSECT);

        canvas.drawPath(pathOpResult,mPaint);
    }

    private void UNION(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-50,0,60, Path.Direction.CW);
        path2.addCircle(50,0,60, Path.Direction.CW);

        pathOpResult.op(path1,path2, Path.Op.UNION);

        canvas.drawPath(pathOpResult,mPaint);
    }

    private void XOR(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.FILL);

        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-50,0,60, Path.Direction.CW);
        path2.addCircle(50,0,60, Path.Direction.CW);

        pathOpResult.op(path1,path2, Path.Op.XOR);

        canvas.drawPath(pathOpResult,mPaint);
    }

    private void computeBounds(Canvas canvas){
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        RectF rect1 = new RectF();              // 存放测量结果的矩形

        Path path = new Path();// 创建Path并添加一些内容

        path.lineTo(100,-50);
        path.lineTo(100,50);
        path.close();

        path.addCircle(-100,0,100, Path.Direction.CW);

        path.computeBounds(rect1,true);         // 测量Path

        canvas.drawPath(path,mPaint);    // 绘制Path

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect1,mPaint);   // 绘制边界
    }

    private void eightDiagrams(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CW);


        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);

        Path path = new Path();

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        path.addCircle(0, 0, 200, Path.Direction.CW);

        canvas.drawPath(path, mPaint);



        Path path5 = new Path();

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        path5.addCircle(0, 100, 10, Path.Direction.CW);

        canvas.drawPath(path5, mPaint);

        Path path6 = new Path();

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        path6.addCircle(0, -100, 10, Path.Direction.CW);

        canvas.drawPath(path6, mPaint);


    }

    public void setShowWhich(int which) {
        this.which = which;
        invalidate();
    }
}
