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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pyj.customview.R;
import com.pyj.customview.view.CustomViewOfBessel;

import java.util.ArrayList;

public class BesselActivity extends AppCompatActivity {

    private CustomViewOfBessel bessel;

    private LinearLayout check;

    private CheckBox checkbox1;

    private int whichPoint = 0;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, BesselActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_bessel);

        bessel = findViewById(R.id.bessel);

        check = findViewById(R.id.check);

        checkbox1 = findViewById(R.id.checkbox1);
        checkbox1.setChecked(true);
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bessel.setMode(true);
                } else {
                    bessel.setMode(false);
                }
            }
        });
    }

    public void choose(View v) {
        setCustomSingleDialog();
    }

    private void setCustomSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView textView = new TextView(this);
        textView.setText("Bessel的方法");
        textView.setTextSize(16);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        float dp_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()); //dp转px
        textView.setPadding((int) (dp_px * 16), (int) (dp_px * 24), (int) (dp_px * 12), 0);
        builder.setCustomTitle(textView);


        final ArrayList<String> chooseTypeTextList = new ArrayList<>();

        chooseTypeTextList.add("quadTo（二阶贝塞尔曲线）");
        chooseTypeTextList.add("cubicTo（三阶贝塞尔曲线）");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.single_choice_item, R.id.text1, chooseTypeTextList);

        builder.setSingleChoiceItems(adapter, whichPoint, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    check.setVisibility(View.VISIBLE);
                    bessel.setTwoBessel(false);
                } else {
                    check.setVisibility(View.GONE);
                    bessel.setTwoBessel(true);
                }

                dialog.dismiss();

                whichPoint = which;

                bessel.setShowWhich(which);
            }
        });

        builder.show();
    }
}
