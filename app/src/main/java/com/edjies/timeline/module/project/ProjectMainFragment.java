package com.edjies.timeline.module.project;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.project.adapter.ProjectMainAdapter;
import com.edjies.timeline.module.project.bean.Project;
import com.edjies.timeline.module.project.dao.ProjectDaoInterface;
import com.edjies.timeline.module.project.fragment.AddProjectDialog;

import java.util.ArrayList;

/**
 *
 * Created by hubble on 2016/11/19 0019.
 */

public class ProjectMainFragment extends Fragment implements View.OnClickListener{
    public final static String TAG = ProjectMainFragment.class.getName();
    public final static String TITLE = "我的项目";

    private RelativeLayout mRlRoot;
    private SwipeRefreshLayout mSWR;
    private RecyclerView mRcvContent;
    private ProjectMainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mFAB;
    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.project_fragment_main, container, false);
        setViews(rootView);
        setListeners();
        initData();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        execGetProject();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab) {
            execAddProject();
        }
    }

    public void initArguments() {
        Bundle arguments = getArguments();
    }

    public void setViews(View rootView) {
        mRlRoot = (RelativeLayout) rootView.findViewById(R.id.rl_root);
        mFAB = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mSWR = (SwipeRefreshLayout)rootView.findViewById(R.id.swr);
        mSWR.setEnabled(false);
        mRcvContent = (RecyclerView) rootView.findViewById(R.id.rcv_content);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ProjectMainAdapter(mActivity, new ArrayList<Project>());
        mRcvContent.setLayoutManager(mLayoutManager);
        mRcvContent.setAdapter(mAdapter);

    }

    public void setListeners() {
        mFAB.setOnClickListener(this);
    }

    public void initData() {

    }

    private void execAddProject() {
        AddProjectDialog newFragment = AddProjectDialog.newInstance();
        newFragment.setOnAddListener(new AddProjectDialog.AddListener() {
            @Override
            public void onAdd(Project item) {
                execGetProject();
            }
        });
        newFragment.show(getFragmentManager(), "dialog");


    }

    private void execGetProject() {
        ArrayList<Project> data = ProjectDaoInterface.getInstance().getMyProjects();
        mAdapter.updateData(data);
    }

    public static ProjectMainFragment newInstance() {
        ProjectMainFragment f = new ProjectMainFragment();
        Bundle arguments = new Bundle();
        f.setArguments(arguments);
        return f;
    }
}
