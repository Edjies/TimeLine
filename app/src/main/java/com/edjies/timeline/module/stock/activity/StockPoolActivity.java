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
import com.edjies.timeline.module.stock.adapter.StockPoolAdapter;
import com.edjies.timeline.module.stock.bean.StockPool;
import com.edjies.timeline.module.stock.bean.StockPoolDao;
import com.edjies.timeline.module.stock.dao.StockDaoInterface;
import com.edjies.timeline.module.stock.fragment.AddStockPoolDialog;

import java.util.ArrayList;

public class StockPoolActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG = StockPoolActivity.class.getSimpleName();
    private RelativeLayout mRlRoot;
    private SwipeRefreshLayout mSWR;
    private RecyclerView mRcvContent;
    private StockPoolAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mFAB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_activity_detail);
        initIntentData();
        setViews();
        setListeners();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_project_item_action, menu);
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
        execGetStockPool();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(R.id.fab == id) {
            execAdd();
        }
    }

    private void initIntentData() {
    }

    private void setViews() {
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        mSWR = (SwipeRefreshLayout)findViewById(R.id.swr);
        mSWR.setEnabled(false);
        mRcvContent = (RecyclerView) findViewById(R.id.rcv_content);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StockPoolAdapter(this, new ArrayList<StockPool>());
        mRcvContent.setLayoutManager(mLayoutManager);
        mRcvContent.setAdapter(mAdapter);
        registerForContextMenu(mRcvContent);
    }



    private void setListeners() {
        mFAB.setOnClickListener(this);
    }

    private void initData() {
        setTitle("Stock Pool");
        StockPoolDao.createTable(MyApplication.getInstance().getDaoSession().getDatabase(), true);

    }

    private void execAdd() {
        AddStockPoolDialog newFragment = AddStockPoolDialog.newInstance();
        newFragment.setOnAddListener(new AddStockPoolDialog.AddListener() {
            @Override
            public void onAdd(StockPool item) {
                execGetStockPool();
            }
        });
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void execDelete(int position) {
        //new AsyncTa
    }

    private void execGetStockPool() {
        ArrayList<StockPool> data = StockDaoInterface.getInstance().getStockPool();
        mAdapter.updateData(data);
    }


    public static void intentMe(Activity activity) {
        Intent intent = new Intent(activity, StockPoolActivity.class);
        Bundle extra = new Bundle();
        intent.putExtras(extra);
        activity.startActivity(intent);
    }
}
