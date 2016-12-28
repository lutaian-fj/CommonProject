package lta.commonproject.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lta.commonproject.R;
import lta.commonproject.data.DataStore;
import lta.commonproject.data.entity.GankResultEntity;
import lta.commonproject.ui.adapter.RecyclerAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
//        data.add("天津");
//        data.add("北京");
//        data.add("福州");
//        data.add("厦门");
//        mRecyclerView.setAdapter(new RecyclerAdapter(mContext,data));
//        Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("今天天气不错啊");
//            }
//        }).flatMap(new Func1<String,Observable<String>>() {
//
//            @Override
//            public Observable<String> call(String s) {
//                return Observable.from(data);
//            }
//        }).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("lta",s);
//            }
//        });
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
                        Log.e("lta","-------------"+gankResultEntity.getResults().size());
                        Log.e("lta","---------"+Thread.currentThread().getName());
                        mRecyclerView.setAdapter(new RecyclerAdapter(mContext,gankResultEntity.getResults()));
                    }
                });
    }

    @Override
    public void addListener() {

    }
}
