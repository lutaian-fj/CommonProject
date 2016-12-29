package lta.commonproject.ui.listener;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/29
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount,lastVisibleItem;

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount(); // 屏幕上可见的item数
        totalItemCount = mLinearLayoutManager.getItemCount(); // 已经加载出来的数量
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition(); // 屏幕上可见的item的第一个
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        Log.e("lta","***************"+loading+"---"+visibleItemCount+"----"+totalItemCount+"----"+firstVisibleItem);
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
