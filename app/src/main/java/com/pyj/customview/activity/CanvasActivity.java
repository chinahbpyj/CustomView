package com.pyj.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pyj.customview.R;

public class CanvasActivity extends AppCompatActivity {

    public static void actionStart(Activity activity){
        Intent intent=new Intent(activity,CanvasActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_canvas);

    }
}
