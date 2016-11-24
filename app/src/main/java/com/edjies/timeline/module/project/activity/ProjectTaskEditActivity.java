package com.edjies.timeline.module.project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.project.bean.ProjectItem;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.utils.UNumber;
import com.edjies.timeline.utils.UToast;

public class ProjectTaskEditActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG = ProjectTaskEditActivity.class.getSimpleName();
    private RelativeLayout mRlRoot;
    private EditText mEtTitle, mEtEt, mEtDetail;
    private ProjectItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_activity_task_edit);
        initIntentData();
        setViews();
        setListeners();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project_task_edit_action, menu);
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
    public void onClick(View view) {
        int id = view.getId();
    }

    private void initIntentData() {
        mItem = (ProjectItem) getIntent().getSerializableExtra("item");
    }

    private void setViews() {
        mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        mEtTitle = (EditText) findViewById(R.id.et_title);
        mEtEt = (EditText) findViewById(R.id.et_et);
        mEtDetail = (EditText) findViewById(R.id.et_detail);

    }

    private void setListeners() {

    }

    private void initData() {
        mEtTitle.setText(mItem.getTitle());
        mEtEt.setText(mItem.getEt() + "");
        mEtDetail.setText(mItem.getDetails());
    }

    private void execSave() {
        String title = mEtTitle.getText().toString();
        int et = UNumber.getInt(mEtEt.getText().toString(), -1);
        String details = mEtDetail.getText().toString();
        if("".equals(title.trim())) {
            UToast.toastError(this, "标题不能为空");
            return;
        }
        if(et < 0) {
            UToast.toastError(this, "预计时间必须大于等于0");
            return;
        }
        mItem.setTitle(title);
        mItem.setEt(et);
        mItem.setDetails(details);
        ProjectDaoInterface.getInstance().updateProjectItem(mItem);
        finish();
    }

    public static void intentMe(Activity activity, ProjectItem item) {
        Intent intent = new Intent(activity, ProjectTaskEditActivity.class);
        Bundle extra = new Bundle();
        extra.putSerializable("item", item);
        intent.putExtras(extra);
        activity.startActivity(intent);
    }
}
