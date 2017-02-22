package com.example.aditya.movie_application;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Aditya on 2/21/2017.
 */

public class TabPager extends FragmentStatePagerAdapter {

    Context context;
    public TabPager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                new MovieRecyclerView(context);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
