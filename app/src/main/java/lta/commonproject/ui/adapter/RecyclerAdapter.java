package lta.commonproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;
import lta.commonproject.data.entity.ResultEntity;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/23
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ResultEntity> mData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context, List<ResultEntity> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0) {
            view  = mLayoutInflater.inflate(R.layout.recyclerview_footer, parent, false);
        }else {
            view  = mLayoutInflater.inflate(R.layout.adapter_recycler, parent, false);
        }
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder) {
            ((FooterViewHolder)holder).mProgressBar.setProgress(0);
        }else if(holder instanceof RecyclerViewHolder) {
            if (mData.get(position) != null)
                ((RecyclerViewHolder)holder).mTitleTv.setText(mData.get(position).getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) == null) {
            Log.e("lta", "结果是空" + position);
            return 0;
        } else {
            return 1;
        }
//        return super.getItemViewType(position);
    }

    public void setDataChange(List<ResultEntity> list) {
//        mData.addAll(list);
        notifyDataSetChanged();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

//        public ProgressWheel mProgressWheel;
        public ProgressBar mProgressBar;
        public FooterViewHolder(View itemView) {
            super(itemView);
//            mProgressWheel = (ProgressWheel) itemView.findViewById(R.id.recyclerview_footer);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.pb);
        }
    }
}
