package com.pyj.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pyj.customview.R;
import com.pyj.customview.bean.PieChartData;
import com.pyj.customview.view.PieChartView;

import java.util.ArrayList;

public class PieChartViewActivity extends AppCompatActivity {

    private PieChartView pieChart;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PieChartViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartview);

        pieChart = findViewById(R.id.pieChart);
        initData();
    }

    private void initData() {
        ArrayList<PieChartData> dataSource = new ArrayList<>();

        PieChartData data1 = new PieChartData();
        data1.setValue(20f);
        dataSource.add(data1);

        PieChartData data2 = new PieChartData();
        data2.setValue(60f);
        dataSource.add(data2);

        PieChartData data3 = new PieChartData();
        data3.setValue(40f);
        dataSource.add(data3);

        PieChartData data4 = new PieChartData();
        data4.setValue(30f);
        dataSource.add(data4);

        PieChartData data5 = new PieChartData();
        data5.setValue(50f);
        dataSource.add(data5);

        PieChartData data6 = new PieChartData();
        data6.setValue(50f);
        dataSource.add(data6);

        PieChartData data7 = new PieChartData();
        data7.setValue(40f);
        dataSource.add(data7);

        PieChartData data8 = new PieChartData();
        data8.setValue(70f);
        dataSource.add(data8);

        PieChartData data9 = new PieChartData();
        data9.setValue(40f);
        dataSource.add(data9);


        pieChart.setDataSource(dataSource);
    }
}
