package com.pyj.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pyj.customview.R;
import com.pyj.customview.view.CheckView;

public class CheckViewActivity extends AppCompatActivity {

    private CheckView checkView;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, CheckViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkview);

        checkView = findViewById(R.id.checkView);

        checkView.start(this);
    }

}
