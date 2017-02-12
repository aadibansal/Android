package com.example.aditya.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Aditya on 2/4/2017.
 */

public class ColorListFragment extends Fragment{

    private static final String ARG_COLORS = "colors";
    private OnColorChosenListener mOnColorChosenListener;

    public static ColorListFragment newInstance(List<Color> colors) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_COLORS, (ArrayList<? extends Parcelable>) colors);
        ColorListFragment colorListFragment = new ColorListFragment();
        colorListFragment.setArguments(args);
        return colorListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnColorChosenListener){
            mOnColorChosenListener  = (OnColorChosenListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutFrozen(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(view instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView) view;
            if(getColors()!=null && getColors().size() > 0){
                recyclerView.setAdapter(new ColorAdapter((List<Color>)getColors()));
            }
        }
    }

    private ArrayList<?> getColors(){
        return getArguments().getParcelableArrayList(ARG_COLORS);
    }

    interface OnColorChosenListener {
        void onColorChosen(Color color);
    }

    private class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

        private final List<Color> mColors;
        private LayoutInflater mLayoutInflater;

        ColorAdapter(List<Color> colors) {
            mColors = colors;
            mLayoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ColorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ColorViewHolder holder, int position) {
            holder.textView.setText(mColors.get(position).name);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnColorChosenListener != null) {
                        mOnColorChosenListener.onColorChosen(mColors.get(holder.getAdapterPosition()));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mColors.size();
        }

        class ColorViewHolder extends RecyclerView.ViewHolder {

            final TextView textView;

            ColorViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(android.R.id.text1);
            }
        }
    }
}
