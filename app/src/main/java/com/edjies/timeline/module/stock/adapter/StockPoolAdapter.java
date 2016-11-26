package com.edjies.timeline.module.stock.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.stock.activity.StockTrackActivity;
import com.edjies.timeline.module.stock.bean.StockPool;

import java.util.ArrayList;


/**
 * @author  hubble
 */

public class StockPoolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private ArrayList<StockPool> mDatas;

    public StockPoolAdapter(BaseActivity context, ArrayList<StockPool> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }


    public void updateData(ArrayList<StockPool> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<StockPool> mDatas) {
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
                vh = new TempViewHolder(mContext.getLayoutInflater().inflate(R.layout.stock_item_pool, parent, false));
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TempViewHolder mHolder = (TempViewHolder) holder;
        final StockPool item = mDatas.get(position);
        mHolder.mTvTitle.setText(item.getName());
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockTrackActivity.intentMe(mContext, item.getName(), item.getId());
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

        public TempViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
