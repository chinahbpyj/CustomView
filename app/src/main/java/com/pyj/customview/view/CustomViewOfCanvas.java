package com.pyj.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewOfCanvas extends View {
    private int mWidth, mHeight;

    private Paint myPaint = new Paint();

    private String text="AllYoga in Beijing";

    public CustomViewOfCanvas(Context context) {
        super(context);
    }

    public CustomViewOfCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        myPaint.setColor(Color.BLUE);
        //myPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(6f);
        myPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画点
        //canvas.drawPoint(200,200,myPaint);

        /*canvas.drawPoints(new float[]{
                100,100,
                200,200,
                300,300,
                300,400,
                300,500,
                300,700,
                300,800,
                300,900,
                400,200,
                500,100
        },myPaint);*/


        //画线
        //canvas.drawLine(100,100,300,300,myPaint);

       /* canvas.drawLines(new float[]{
                100,100,300,300,
                300,300,500,100,
                300,300,300,900
        },myPaint);*/


        //画矩形
        //canvas.drawRect(100,100,600,600,myPaint);

       /* Rect rect=new Rect(100,100,600,600);
        canvas.drawRect(rect,myPaint);*/

       /* RectF rectF=new RectF(100,100,600,600);
        canvas.drawRect(rectF,myPaint);*/


        //绘制圆角矩形
        //canvas.drawRoundRect(100,100,400,400,20,20,myPaint);

       /* RectF rectF=new RectF(100,100,400,400);
        canvas.drawRoundRect(rectF,20,20,myPaint);*/


        //绘制椭圆
        //canvas.drawOval(100,100,800,400,myPaint);

        /*RectF rectF=new RectF(10,10,540,400);
        canvas.drawOval(rectF,myPaint);*/


        //绘制圆
        // canvas.drawCircle(300,300,200,myPaint);


        //绘制圆弧
        /*RectF rectF=new RectF(100,100,400,400);

        myPaint.setColor(Color.GRAY);
        activty_canvas.drawRect(rectF,myPaint);

        myPaint.setColor(Color.BLUE);
        activty_canvas.drawArc(rectF,0f,120f,true,myPaint);

        myPaint.setColor(Color.RED);
        activty_canvas.drawArc(rectF,120f,240f,true,myPaint);*/


        //绘制一个圆，只要边不要里面的颜色
        /*STROKE                //描边
          FILL                  //填充
          FILL_AND_STROKE       //描边加填充*/
        /*myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(400,400,200,myPaint);*/


        //位移(translate)(对坐标系原点的位移)
       /* myPaint.setColor(Color.BLACK);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,myPaint);

        myPaint.setColor(Color.RED);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,myPaint);*/


        //缩放(scale)(缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴)
       /* canvas.translate(mWidth/2,mHeight/2);

        RectF rectF=new RectF(0,-200,200,0);
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,myPaint);

        canvas.scale(-0.5f,-0.5f,100,0);

        myPaint.setColor(Color.RED);
        canvas.drawRect(rectF,myPaint);*/

       /* myPaint.setColor(Color.BLACK);

        canvas.translate(mWidth/2,mHeight/2);

        RectF rectF=new RectF(-300,-300,300,300);

        for(int i=0;i<30;i++){
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rectF,myPaint);
        }*/


        //旋转(rotate)
       /* canvas.translate(mWidth/2,mHeight/2);

        RectF rectF=new RectF(0,-200,200,0);

        myPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF,myPaint);

        canvas.rotate(90,100,0);

        myPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,myPaint);*/

       /* canvas.translate(mWidth / 2, mHeight / 2);
        myPaint.setColor(Color.BLACK);

        canvas.drawCircle(0, 0, 200, myPaint);
        canvas.drawCircle(0, 0, 180, myPaint);

        for (int i = 0; i < 360; i += 10) {
            canvas.drawLine(0, 180, 0, 200, myPaint);
            canvas.rotate(10);
        }*/




       //绘制文字
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setColor(Color.BLACK);
        myPaint.setTextSize(50f);
        //myPaint.setTextAlign(Paint.Align.CENTER);

        //public void drawText (String text, float x, float y, Paint paint)
        //文本基线位置(基线x默认在字符串左侧，基线y默认在字符串下方)。
        //canvas.drawText(text,100,100,myPaint);

        //public void drawText (String text, int start, int end, float x, float y, Paint paint)
        //使用start和end指定的区间是前闭后开的，即包含start指定的下标，而不包含end指定的下标
        canvas.drawText(text,3,text.length(),100,100,myPaint);




    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }
}
