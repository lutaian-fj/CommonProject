package lta.commonproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/23
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context,List<String> data) {
        mContext = context;
        mData.addAll(data);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_recycler,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mTitleTv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTv;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
