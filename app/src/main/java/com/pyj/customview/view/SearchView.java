package com.pyj.customview.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SearchView extends View {
    // 这个视图拥有的状态
    public enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    // 画笔
    private Paint mPaint;

    // View 宽高
    private int mViewWidth;
    private int mViewHeight;

    // 当前的状态(非常重要)
    private State mCurrentState = State.NONE;

    // 放大镜与外部圆环
    private Path path_srarch;
    private Path path_circle;

    // 测量Path 并截取部分的工具
    private PathMeasure mMeasure;

    // 控制各个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    // 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
    private float mAnimatorValue = 0;

    // 动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAll();
    }

    private void initAll() {
        initPaint();

        initPath();

        initListener();

        initAnimator();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }

    private void initPath() {
        path_srarch = new Path();
        path_circle = new Path();

        mMeasure = new PathMeasure();

        // 注意,不要到360度,否则内部会自动优化,测量不能取到需要的数值
        RectF oval1 = new RectF(-50, -50, 50, 50);          // 放大镜圆环
        path_srarch.addArc(oval1, 45, 359.999f);

        RectF oval2 = new RectF(-100, -100, 100, 100);      // 外部圆环
        path_circle.addArc(oval2, 45, 359.999f);

        float[] pos = new float[2];

        mMeasure.setPath(path_circle, false);               // 放大镜把手的位置
        mMeasure.getPosTan(0, pos, null);

        path_srarch.lineTo(pos[0], pos[1]);                 // 放大镜把手
    }

    private void initListener() {
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        };
    }

    private void animator() {
        switch (mCurrentState) {
            case STARTING:
                mStartingAnimator.removeUpdateListener(mUpdateListener);
                mSearchingAnimator.addUpdateListener(mUpdateListener);
                // 从开始动画转换好搜索动画
                mCurrentState = State.SEARCHING;
                mSearchingAnimator.start();
                break;

            case SEARCHING:
                break;

            case ENDING:
                // 从结束动画转变为无状态
                mCurrentState = State.NONE;
                mEndingAnimator.removeUpdateListener(mUpdateListener);
                break;
        }
    }

    private void initAnimator() {
        int defaultDuration = 2000;

        mStartingAnimator = ValueAnimator.ofFloat(0, 1)
                .setDuration(defaultDuration);

        mSearchingAnimator = ValueAnimator.ofFloat(0, 1)
                .setDuration(defaultDuration);
        mSearchingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mSearchingAnimator.setRepeatMode(ValueAnimator.RESTART);

        mEndingAnimator = ValueAnimator.ofFloat(1, 0)
                .setDuration(defaultDuration);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_srarch, mPaint);
                break;

            case STARTING:
                mMeasure.setPath(path_srarch, false);
                Path dst = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst, true);
                canvas.drawPath(dst, mPaint);
                break;

            case SEARCHING:
                mMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));

                // float start =(float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mMeasure.getLength()));

                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;

            case ENDING:
                mMeasure.setPath(path_srarch, false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }

    public void startSearch() {
        if (mCurrentState == State.ENDING ||
                mCurrentState == State.STARTING) {
            return;
        }

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    public void endSearch() {
        if (mCurrentState == State.STARTING ||
                mCurrentState == State.ENDING) {
            return;
        }

        mSearchingAnimator.removeUpdateListener(mUpdateListener);
        mSearchingAnimator.end();
        mEndingAnimator.addUpdateListener(mUpdateListener);
        mCurrentState = State.ENDING;
        mEndingAnimator.start();
    }

    public void removeAll() {
        mStartingAnimator.removeUpdateListener(mUpdateListener);
        mStartingAnimator.end();

        mSearchingAnimator.removeUpdateListener(mUpdateListener);
        mSearchingAnimator.end();

        mEndingAnimator.removeUpdateListener(mUpdateListener);
        mEndingAnimator.end();

        mAnimatorListener.onAnimationCancel(mStartingAnimator);
        mAnimatorListener.onAnimationCancel(mSearchingAnimator);
        mAnimatorListener.onAnimationCancel(mEndingAnimator);
    }
}