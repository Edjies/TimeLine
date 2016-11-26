package com.edjies.timeline.module.stock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.MyApplication;
import com.edjies.timeline.R;
import com.edjies.timeline.module.stock.adapter.StockTrackAdapter;
import com.edjies.timeline.module.stock.bean.StockTrack;
import com.edjies.timeline.module.stock.bean.StockTrackDao;
import com.edjies.timeline.module.stock.dao.StockDaoInterface;
import com.edjies.timeline.module.stock.fragment.AddStockTrackDialog;

import java.util.ArrayList;

public class StockTrackActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG = StockTrackActivity.class.getSimpleName();
    private RelativeLayout mRlRoot;
    private SwipeRefreshLayout mSWR;
    private RecyclerView mRcvContent;
    private StockTrackAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mFAB;

    private Long mStockPoolId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_activity_track);
        initIntentData();
        setViews();
        setListeners();
        initData();
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:  // delete
                execDelete(item.getItemId());
                execGetData();
                break;
            case 1: // edit
               // ProjectTaskEditActivity.intentMe(this, mAdapter.getData().get(item.getItemId()));
                break;

        }
        //execDelete(item.getItemId());
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_project_item_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        execGetData();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(R.id.fab == id) {
            execAdd();
        }
    }

    private void initIntentData() {
        setTitle(getIntent().getExtras().getString("title"));
        mStockPoolId = getIntent().getExtras().getLong("stockPoolId");
    }

    private void setViews() {
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        mSWR = (SwipeRefreshLayout)findViewById(R.id.swr);
        mSWR.setEnabled(false);
        mRcvContent = (RecyclerView) findViewById(R.id.rcv_content);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StockTrackAdapter(this, new ArrayList<StockTrack>());
        mRcvContent.setLayoutManager(mLayoutManager);
        mRcvContent.setAdapter(mAdapter);
        registerForContextMenu(mRcvContent);
    }



    private void setListeners() {
        mFAB.setOnClickListener(this);
    }

    private void initData() {
        StockTrackDao.createTable(MyApplication.getInstance().getDaoSession().getDatabase(), true);
    }

    private void execAdd() {
        AddStockTrackDialog newFragment = AddStockTrackDialog.newInstance(mStockPoolId);
        newFragment.setOnAddListener(new AddStockTrackDialog.AddListener() {
            @Override
            public void onAdd(StockTrack item) {
                execGetData();
            }
        });
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void execDelete(int position) {
        StockDaoInterface.getInstance().deleteStockTrack(mAdapter.getDatas().get(position));
    }

    private void execGetData() {
        ArrayList<StockTrack> data = StockDaoInterface.getInstance().getStockTrack(mStockPoolId);
        mAdapter.updateData(data);
    }

    public static void intentMe(Activity activity, String title, long stockPoolId) {
        Intent intent = new Intent(activity, StockTrackActivity.class);
        Bundle extra = new Bundle();
        extra.putString("title", title);
        extra.putLong("stockPoolId", stockPoolId);
        intent.putExtras(extra);
        activity.startActivity(intent);
    }
}
