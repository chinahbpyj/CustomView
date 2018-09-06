package com.pyj.customview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pyj.customview.R;

import java.util.Timer;
import java.util.TimerTask;

public class CheckView extends View {
    private Paint mPaint;
    private Bitmap okBitmap;

    private int mWidth, mHeight;

    private int animCurrentPage = 0;       // 当前页码
    private int animMaxPage = 13;           // 总页数

    private Timer timer;
    private TimerTask timerTask;

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context mContext) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.checkmark);
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

        canvas.translate(mWidth / 2, mHeight / 2);

        // 绘制背景圆形
        canvas.drawCircle(0, 0, 60, mPaint);

        // 得出图像边长
        int sideLength = okBitmap.getHeight();

        // 得到图像选区 和 实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0,
                sideLength * (animCurrentPage + 1), sideLength);

        Rect dst = new Rect(-50, -50,
                50, 50);

        // 绘制
        canvas.drawBitmap(okBitmap, src, dst, null);

        animCurrentPage++;
    }

    public void start(final Activity activity) {
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (animCurrentPage == animMaxPage) {
                            destroy();
                            return;
                        }
                        invalidate();
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 100);
    }

    public void stop() {
        destroy();
    }

    private void destroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
