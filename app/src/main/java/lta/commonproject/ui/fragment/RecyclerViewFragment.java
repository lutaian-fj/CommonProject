package lta.commonproject.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewChildAttachStateChangeEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;
import lta.commonproject.ui.adapter.RecyclerAdapter;
import rx.functions.Action1;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/23
 */

public class RecyclerViewFragment extends BaseFragment {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<String> data = new ArrayList<>();
    @Override
    public int initResource() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void initComponent(View view) {
        mContext = this.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void initData() {
        data.add("天津");
        data.add("北京");
        data.add("福州");
        data.add("厦门");
        mRecyclerView.setAdapter(new RecyclerAdapter(mContext,data));
        RxRecyclerView.childAttachStateChangeEvents(mRecyclerView).subscribe(new Action1<RecyclerViewChildAttachStateChangeEvent>() {
            @Override
            public void call(RecyclerViewChildAttachStateChangeEvent recyclerViewChildAttachStateChangeEvent) {
                Log.e("lta","------------------------------");
            }
        });
    }

    @Override
    public void addListener() {

    }
}
