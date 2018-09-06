package com.pyj.customview.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pyj.customview.R;
import com.pyj.customview.view.CustomViewOfCanvas;

import java.util.ArrayList;

public class CanvasActivity extends AppCompatActivity implements View.OnLongClickListener {

    private CustomViewOfCanvas canvas;
    private int whichPoint = 0;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, CanvasActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_canvas);

        canvas = findViewById(R.id.canvas);

        canvas.setOnLongClickListener(this);
    }

    private void setCustomSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView textView = new TextView(this);
        textView.setText("canvas的方法");
        textView.setTextSize(16);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        float dp_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()); //dp转px
        textView.setPadding((int) (dp_px * 16), (int) (dp_px * 24), (int) (dp_px * 12), 0);
        builder.setCustomTitle(textView);


        final ArrayList<String> chooseTypeTextList = new ArrayList<>();

        chooseTypeTextList.add("drawPoint");
        chooseTypeTextList.add("drawPoints");
        chooseTypeTextList.add("drawLine");
        chooseTypeTextList.add("drawLines");
        chooseTypeTextList.add("drawRect");
        chooseTypeTextList.add("drawRoundRect");
        chooseTypeTextList.add("drawOval");
        chooseTypeTextList.add("drawCircle");
        chooseTypeTextList.add("drawArc");
        chooseTypeTextList.add("translate");
        chooseTypeTextList.add("scale");
        chooseTypeTextList.add("rotate");
        chooseTypeTextList.add("drawText");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.single_choice_item, R.id.text1, chooseTypeTextList);

        builder.setSingleChoiceItems(adapter, whichPoint, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                whichPoint = which;

                canvas.setShowWhich(which);
            }
        });

        builder.show();
    }

    @Override
    public boolean onLongClick(View v) {
        setCustomSingleDialog();
        return true;
    }
}
