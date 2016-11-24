package com.edjies.timeline.module.project.adapter;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.project.activity.ProjectDetailActivity;
import com.edjies.timeline.module.project.bean.Project;
import com.edjies.timeline.module.project.bean.ProjectItem;
import com.edjies.timeline.module.project.bean.ProjectStatus;
import com.edjies.timeline.utils.ULog;
import com.edjies.timeline.utils.UToast;

import java.util.ArrayList;


/**
 * @author  hubble
 */

public class ProjectDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private ArrayList<ProjectItem> mDatas;

    public ProjectDetailAdapter(BaseActivity context, ArrayList<ProjectItem> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    public ArrayList<ProjectItem> getData() {
        return mDatas;
    }


    public void updateData(ArrayList<ProjectItem> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addData(ProjectItem item) {
        mDatas.add(item);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<ProjectItem> mDatas) {
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
                vh = new TempViewHolder(mContext.getLayoutInflater().inflate(R.layout.project_item_detail, parent, false));
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TempViewHolder mHolder = (TempViewHolder) holder;

        final ProjectItem item = mDatas.get(position);
        mHolder.mTvTitle.setText(item.getTitle());
        if(item.getStatus() == ProjectStatus.STATUS_CPT) {
            mHolder.mCbStatus.setChecked(true);

            mHolder.mCbStatus.setEnabled(false);
        }else {
            mHolder.mCbStatus.setChecked(false);
            mHolder.mCbStatus.setEnabled(true);
        }
        if(item.getEt() != 0) {
            mHolder.mTvET.setText(item.getEt() + "'");
        }else {
            mHolder.mTvET.setText("");
        }

        mHolder.mCbStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    item.setStatus(ProjectStatus.STATUS_CPT);
                }else {
                    item.setStatus(ProjectStatus.STATUS_TOD);
                }
            }
        });

        mHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                ULog.i("timelinei", "menu" + position);
                menu.add(0, position, 0, "删除");
                menu.add(0, position, 1, "编辑");
            }
        });

        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(item.getDetails()) && item.getDetails() != null) {
                    new AlertDialog.Builder(mContext).setIcon(R.drawable.ic_list).setTitle("任务详情")
                    .setMessage(item.getDetails()).setPositiveButton("确定", null).show();
                }
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

    private static class TempViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvTitle;
        private CheckBox mCbStatus;
        private TextView mTvET;

        public TempViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mCbStatus = (CheckBox) itemView.findViewById(R.id.cb_status);
            mTvET = (TextView) itemView.findViewById(R.id.tv_et);

        }
    }

}
