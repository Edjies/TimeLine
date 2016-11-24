package com.edjies.timeline.module.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;

public class TeimplateActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG = TeimplateActivity.class.getSimpleName();
    private RelativeLayout mRlRoot;
    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teimplate_activity);
        initIntentData();
        setViews();
        setListeners();
        initData();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }

    private void initIntentData() {

    }

    private void setViews() {
        mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setListeners() {
        mFAB.setOnClickListener(this);
    }

    private void initData() {

    }

    public static void intentMe(Activity activity) {
        Intent intent = new Intent(activity, TeimplateActivity.class);
        Bundle extra = new Bundle();
        intent.putExtras(extra);
        activity.startActivity(intent);
    }
}
