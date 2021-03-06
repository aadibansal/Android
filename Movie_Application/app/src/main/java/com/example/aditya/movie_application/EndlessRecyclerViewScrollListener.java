package com.example.aditya.movie_application;

/**
 * Created by Aditya on 2/19/2017.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    // The current offset index of data you have loaded
    private int currentPage = 0;

    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;

    // Sets the starting page index
    private int startingPageIndex = 0;

    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;

    RecyclerView.LayoutManager mLayoutManager;

    Context context;

    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager, Context context) {
        this.mLayoutManager = layoutManager;
        this.context = context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        int totalItemCount = mLayoutManager.getItemCount();

        if(!loading && lastVisibleItemPosition+this.context.getResources().getInteger(R.integer.numColumns) > totalItemCount){
            currentPage++;
            totalItemCount+=20;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }
        /*lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        lastVisibleItemPosition += this.context.getResources().getInteger(R.integer.numColumns) - 1;

        if (lastVisibleItemPosition < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition == totalItemCount)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }*/
    }
        // Defines the process for actually loading more data based on page
        public abstract void onLoadMore(int page, int totalItemsCount);
}
