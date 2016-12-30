package lta.commonproject.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lta.commonproject.R;
import lta.commonproject.data.DataStore;
import lta.commonproject.data.entity.GankResultEntity;
import lta.commonproject.data.entity.ResultEntity;
import lta.commonproject.ui.adapter.RecyclerAdapter;
import lta.commonproject.ui.listener.EndlessRecyclerOnScrollListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

;


/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/23
 */

public class RecyclerViewFragment extends BaseFragment {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ResultEntity> data = new ArrayList<>();
    private List<ResultEntity> results = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;
    @Override
    public int initResource() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void initComponent(View view) {
        mContext = this.getContext();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
    }

    @Override
    public void initData() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE);
        DataStore.getInstance().getAndroidData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResultEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.e("lta","------completed-------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("lta","------error-------"+e.toString());
                    }

                    @Override
                    public void onNext(GankResultEntity gankResultEntity) {
                        results = gankResultEntity.getResults();
                        data.addAll(results);
                        data.add(null);
                        mRecyclerAdapter = new RecyclerAdapter(mContext,data);
                        mRecyclerView.setAdapter(mRecyclerAdapter);
                    }
                });
    }

    @Override
    public void addListener() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext,"下拉刷新",Toast.LENGTH_SHORT).show();
                Observable.timer(3, TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                        .map(new Func1<Long, Object>() {

                            @Override
                            public Object call(Long aLong) {
                                mSwipeRefreshLayout.setRefreshing(false);
                                return null;
                            }
                        }).subscribe();
            }
        });


        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Toast.makeText(mContext,"加载更多",Toast.LENGTH_SHORT).show();
                Observable.timer(2,TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                        .map(new Func1<Long, Object>() {
                            @Override
                            public Object call(Long aLong) {
                                data.remove(data.size() - 1);
                                data.addAll(results);
                                data.add(null);
                                mRecyclerAdapter.notifyDataSetChanged();
                                return null;
                            }
                        })
                        .subscribe();
            }
        });
    }
}
