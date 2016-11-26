package com.edjies.timeline.module.stock.adapter;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edjies.timeline.BaseActivity;
import com.edjies.timeline.R;
import com.edjies.timeline.module.stock.bean.StockTrack;
import com.edjies.timeline.utils.UDate;

import java.util.ArrayList;


/**
 * @author  hubble
 */

public class StockTrackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private ArrayList<StockTrack> mDatas;

    public StockTrackAdapter(BaseActivity context, ArrayList<StockTrack> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }


    public void updateData(ArrayList<StockTrack> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<StockTrack> mDatas) {
        if(mDatas == null) {
            this.mDatas = mDatas;
        }else {
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }

    public ArrayList<StockTrack> getDatas() {
        return mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        switch (viewType) {
            default:
                vh = new TempViewHolder(mContext.getLayoutInflater().inflate(R.layout.stock_item_track, parent, false));
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TempViewHolder mHolder = (TempViewHolder) holder;

        final StockTrack item = mDatas.get(position);
        mHolder.mTvCode.setText(item.getCode());
        mHolder.mTvHigh.setText(String.valueOf(item.getHigh()));
        mHolder.mTvLow.setText(String.valueOf(item.getLow()));
        mHolder.mTvTime.setText(UDate.getFormatDate(item.getStart(), "yyyy-MM-dd", "MM-dd")
        + "至"
        +UDate.getFormatDate(item.getEnd(), "yyyy-MM-dd", "MM-dd"));
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // show desc
                new AlertDialog.Builder(mContext).setTitle("追踪说明").setIcon(R.drawable.ic_list).setMessage(item.getDesc()).setPositiveButton("确定", null).show();
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
        private TextView mTvCode;
        private TextView mTvHigh;
        private TextView mTvLow;
        private TextView mTvTime;

        public TempViewHolder(final View itemView) {
            super(itemView);
            mTvCode = (TextView) itemView.findViewById(R.id.tv_code);
            mTvHigh = (TextView) itemView.findViewById(R.id.tv_high);
            mTvLow = (TextView) itemView.findViewById(R.id.tv_low);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_duration);

        }
    }

}
