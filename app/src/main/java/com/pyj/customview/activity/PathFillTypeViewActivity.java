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
import com.pyj.customview.view.PathFillTypeView;

import java.util.ArrayList;

public class PathFillTypeViewActivity extends AppCompatActivity implements View.OnLongClickListener {

    private PathFillTypeView fillType;
    private int whichPoint = 0;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PathFillTypeViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_filltype);

        fillType = findViewById(R.id.fillType);

        fillType.setOnLongClickListener(this);
    }

    private void setCustomSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView textView = new TextView(this);
        textView.setText("Path的填充模式（filltype）");
        textView.setTextSize(16);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        float dp_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()); //dp转px
        textView.setPadding((int) (dp_px * 16), (int) (dp_px * 24), (int) (dp_px * 12), 0);
        builder.setCustomTitle(textView);


        final ArrayList<String> chooseTypeTextList = new ArrayList<>();

        chooseTypeTextList.add("even_odd");
        chooseTypeTextList.add("inverse_even_odd");
        chooseTypeTextList.add("winding");

        chooseTypeTextList.add("布尔操作#DIFFERENCE(差集)");
        chooseTypeTextList.add("布尔操作#REVERSE_DIFFERENCE(差集)");
        chooseTypeTextList.add("布尔操作#INTERSECT(交集)");
        chooseTypeTextList.add("布尔操作#UNION(并集)");
        chooseTypeTextList.add("布尔操作#XOR(异或)");

        chooseTypeTextList.add("computeBounds");

        chooseTypeTextList.add("eightDiagrams");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.single_choice_item, R.id.text1, chooseTypeTextList);

        builder.setSingleChoiceItems(adapter, whichPoint, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                whichPoint = which;

                fillType.setShowWhich(which);
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
