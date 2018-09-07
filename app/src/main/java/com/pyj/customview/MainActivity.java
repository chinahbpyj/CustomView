package com.pyj.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pyj.customview.activity.BesselActivity;
import com.pyj.customview.activity.CanvasActivity;
import com.pyj.customview.activity.CheckViewActivity;
import com.pyj.customview.activity.HorizontalProgressBarActivity;
import com.pyj.customview.activity.PathActivity;
import com.pyj.customview.activity.PathFillTypeViewActivity;
import com.pyj.customview.activity.PathMeasureActivity;
import com.pyj.customview.activity.PieChartViewActivity;
import com.pyj.customview.activity.RadarViewActivity;
import com.pyj.customview.activity.SearchViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void canvas(View v) {
        CanvasActivity.actionStart(this);
    }

    public void horizontalProgressBar(View v) {
        HorizontalProgressBarActivity.actionStart(this);
    }

    public void pieChartView(View v) {
        PieChartViewActivity.actionStart(this);
    }

    public void checkView(View v) {
        CheckViewActivity.actionStart(this);
    }

    public void path(View v) {
        PathActivity.actionStart(this);
    }

    public void radarView(View v) {
        RadarViewActivity.actionStart(this);
    }

    public void bessel(View v) {
        BesselActivity.actionStart(this);
    }

    public void pathFillType(View v) {
        PathFillTypeViewActivity.actionStart(this);
    }

    public void pathMeasure(View v){
        PathMeasureActivity.actionStart(this);
    }

    public void searchView(View v){
        SearchViewActivity.actionStart(this);
    }

}
