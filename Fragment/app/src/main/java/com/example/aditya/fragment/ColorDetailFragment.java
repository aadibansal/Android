package com.example.aditya.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aditya on 2/4/2017.
 */

public class ColorDetailFragment extends Fragment {

    private static final String ARG_SELECTED_COLOR_CODE = "color_code";

    public static ColorDetailFragment newInstance(String colorCode) {

        Bundle args = new Bundle();
        args.putString(ARG_SELECTED_COLOR_CODE, colorCode);
        ColorDetailFragment fragment = new ColorDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new View(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(android.graphics.Color.parseColor(getSelectedColorCode()));
    }

    private String getSelectedColorCode() {
        return getArguments().getString(ARG_SELECTED_COLOR_CODE);
    }
}
