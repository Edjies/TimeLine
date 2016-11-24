package com.edjies.timeline.module.template;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;

import java.util.ArrayList;


/**
 * @author  hubble
 */

public class TemplateRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private ArrayList<Object> mDatas;

    public TemplateRecycleAdapter(BaseActivity context, ArrayList<Object> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }


    public void updateData(ArrayList<Object> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<Object> mDatas) {
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
                vh = new TempViewHolder(mContext.getLayoutInflater().inflate(R.layout.template_item, parent, false));
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // TODO 根据位置获取相应的ViewHolder
        //TempViewHolder mHolder = (TempViewHolder) holder;
        // TODO 填充数据
        //Object item = mDatas.get(position);
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
        // TODO 根据位置确定布局类型
        return 0;
    }

    private static class TempViewHolder extends RecyclerView.ViewHolder {

        public TempViewHolder(final View itemView) {
            super(itemView);
            // TODO get view object
        }
    }

}
