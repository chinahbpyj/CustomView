package com.pyj.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pyj.customview.R;
import com.pyj.customview.view.HorizontalProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class HorizontalProgressBarActivity extends AppCompatActivity {

    private HorizontalProgressBar bar;
    private int count;
    private Timer timer;
    private TimerTask timerTask;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, HorizontalProgressBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalprogressbar);

        bar = findViewById(R.id.bar);

        initHorizontalProgressBar();
    }

    private void initHorizontalProgressBar() {
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                count++;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count > 100) {
                            destroy();
                            return;
                        }

                        bar.setCurrentProgress(count);
                    }
                });


            }
        };

        timer.schedule(timerTask, 0, 200);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }
}
