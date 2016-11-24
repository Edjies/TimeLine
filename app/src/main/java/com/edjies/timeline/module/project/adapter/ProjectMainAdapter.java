package com.edjies.timeline.module.project.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.project.activity.ProjectDetailActivity;
import com.edjies.timeline.module.project.bean.Project;

import java.util.ArrayList;


/**
 * @author  hubble
 */

public class ProjectMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private ArrayList<Project> mDatas;

    public ProjectMainAdapter(BaseActivity context, ArrayList<Project> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }


    public void updateData(ArrayList<Project> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<Project> mDatas) {
        if(mDatas == null) {
            this.mDatas = mDatas;
        }else {
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        switch (viewType) {
            default:
                vh = new TempViewHolder(mContext.getLayoutInflater().inflate(R.layout.project_item_main, parent, false));
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TempViewHolder mHolder = (TempViewHolder) holder;

        final Project item = mDatas.get(position);
        mHolder.mTvTitle.setText(item.getTitle());
        mHolder.mTvStatics.setText(item.getFinished() + "/" + item.getTotal());
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectDetailActivity.intentMe(mContext, item.getTitle(), item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
         return mDatas.size();
    }

    /**
     * 当recylerView 中有不止一种View类型需要展示时， 可通过重写该方法指明每一个位置需要的View的类型</br>
     * 该方法默认返回0， 代表只有单一的view类型</br>
     * 建议：</br>
     * 可用不同的view的布局资源id来标识不同的类型
     * @param position 位置
     * @return 返回布局类型
     */
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    private static class TempViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvStatics;

        public TempViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvStatics = (TextView) itemView.findViewById(R.id.tv_statics);
        }
    }

}
