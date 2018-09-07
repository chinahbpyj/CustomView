package com.pyj.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pyj.customview.R;
import com.pyj.customview.view.SearchView;

public class SearchViewActivity extends AppCompatActivity {

    private SearchView searchView;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, SearchViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        searchView = findViewById(R.id.searchView);
    }

    public void start(View v){
        searchView.startSearch();
    }


    public void end(View v){
        searchView.endSearch();
    }
}
