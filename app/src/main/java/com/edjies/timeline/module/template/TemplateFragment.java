package com.edjies.timeline.module.template;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;

/**
 *
 * Created by hubble on 2016/11/19 0019.
 */

public class TemplateFragment extends Fragment implements View.OnClickListener{
    public final static String TAG = TemplateFragment.class.getName();
    private RelativeLayout mRlRoot;
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
        View rootView = inflater.inflate(R.layout.template_fragment_main, container, false);
        setViews(rootView);
        setListeners();
        initData();
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    public void initArguments() {
        Bundle arguments = getArguments();
    }

    public void setViews(View rootView) {
        mRlRoot = (RelativeLayout) rootView.findViewById(R.id.rl_root);
    }

    public void setListeners() {

    }

    public void initData() {

    }

    public static TemplateFragment newInstance() {
        TemplateFragment f = new TemplateFragment();
        Bundle arguments = new Bundle();
        f.setArguments(arguments);
        return f;
    }
}
