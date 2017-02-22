package com.example.aditya.movie_application;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Aditya on 2/19/2017.
 */

public class SpacesItemDecroation extends RecyclerView.ItemDecoration {

    private int space;
    private Context context;

    public SpacesItemDecroation(int space, Context context) {
        this.space = space;
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
        int numOfColumns = context.getResources().getInteger(R.integer.numColumns);
        if(parent.getChildLayoutPosition(view) % numOfColumns ==0){
            outRect.left = space;
        }
        outRect.bottom = space;
        if (parent.getChildLayoutPosition(view) < numOfColumns) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
