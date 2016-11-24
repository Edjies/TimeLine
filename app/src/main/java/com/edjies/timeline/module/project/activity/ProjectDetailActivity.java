package com.edjies.timeline.module.project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.project.adapter.ProjectDetailAdapter;
import com.edjies.timeline.module.project.adapter.ProjectMainAdapter;
import com.edjies.timeline.module.project.bean.Project;
import com.edjies.timeline.module.project.bean.ProjectItem;
import com.edjies.timeline.module.project.bean.ProjectItemDao;
import com.edjies.timeline.module.project.bean.ProjectStatus;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.module.project.fragment.AddProjectDialog;
import com.edjies.timeline.module.project.fragment.AddProjectItemDialog;
import com.edjies.timeline.utils.UToast;

import java.util.ArrayList;

public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG = ProjectDetailActivity.class.getSimpleName();
    private RelativeLayout mRlRoot;
    private SwipeRefreshLayout mSWR;
    private RecyclerView mRcvContent;
    private ProjectDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mFAB;

    private Long mProjectId;

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
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:  // delete
                execDelete(item.getItemId());
                execGetProjectDetail();
                break;
            case 1: // edit
                ProjectTaskEditActivity.intentMe(this, mAdapter.getData().get(item.getItemId()));
                break;

        }
        //execDelete(item.getItemId());
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project_item_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            execSave();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        execGetProjectDetail();
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
        mProjectId = getIntent().getExtras().getLong("projectId");
    }

    private void setViews() {
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        mSWR = (SwipeRefreshLayout)findViewById(R.id.swr);
        mSWR.setEnabled(false);
        mRcvContent = (RecyclerView) findViewById(R.id.rcv_content);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProjectDetailAdapter(this, new ArrayList<ProjectItem>());
        mRcvContent.setLayoutManager(mLayoutManager);
        mRcvContent.setAdapter(mAdapter);
        registerForContextMenu(mRcvContent);
    }



    private void setListeners() {
        mFAB.setOnClickListener(this);
    }

    private void initData() {
        ArrayList<ProjectItem> data = new ArrayList<>();
        mAdapter.updateData(data);
    }

    private void execAdd() {
        AddProjectItemDialog newFragment = AddProjectItemDialog.newInstance(mProjectId);
        newFragment.setOnAddListener(new AddProjectItemDialog.AddListener() {
            @Override
            public void onAdd(ProjectItem item) {
                execGetProjectDetail();
            }
        });
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void execDelete(int position) {
        ProjectDaoInterface.getInstance().deleteProjectItem(mAdapter.getData().get(position));
    }

    private void execGetProjectDetail() {
        ArrayList<ProjectItem> data = ProjectDaoInterface.getInstance().getMyProjectDetails(mProjectId);
        mAdapter.updateData(data);
    }

    private void execSave() {
        ProjectDaoInterface.getInstance().updateProjectItem(mAdapter.getData());
    }

    public static void intentMe(Activity activity, String title, long projectId) {
        Intent intent = new Intent(activity, ProjectDetailActivity.class);
        Bundle extra = new Bundle();
        extra.putString("title", title);
        extra.putLong("projectId", projectId);
        intent.putExtras(extra);
        activity.startActivity(intent);
    }
}
