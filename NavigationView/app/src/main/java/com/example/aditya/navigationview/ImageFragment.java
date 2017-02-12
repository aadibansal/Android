package com.example.aditya.navigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Aditya on 2/5/2017.
 */

public class ImageFragment extends Fragment {
    private static final String ARG_DISPLAY_TYPE = "display_type";
    private DisplayType mDisplayType;

    private int[] flowerResources;
    private int[] natureResources;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(DisplayType displayType) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DISPLAY_TYPE, displayType.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mDisplayType = DisplayType.values()[getArguments().getInt(ARG_DISPLAY_TYPE)];
        }
        flowerResources = new int[]{
                R.drawable.ic_flower1, R.drawable.ic_flower2,
                R.drawable.ic_flower3, R.drawable.ic_flower4,
                R.drawable.ic_flower5, R.drawable.ic_flower6
        };

        natureResources = new int[]{
                R.drawable.ic_nature1, R.drawable.ic_nature2,
                R.drawable.ic_nature3, R.drawable.ic_nature4,
                R.drawable.ic_nature5, R.drawable.ic_nature6
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(container.getContext());
        RecyclerView.LayoutManager layoutManager;
        if (mDisplayType == DisplayType.FLOWERS) {
            recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(),
                    DividerItemDecoration.HORIZONTAL));
            layoutManager = new GridLayoutManager(container.getContext(), 2);
        } else {
            recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(),
                    DividerItemDecoration.VERTICAL));
            layoutManager = new LinearLayoutManager(container.getContext());
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ItemAdapter(mDisplayType == DisplayType.FLOWERS ?
                flowerResources : natureResources));
        return recyclerView;
    }

    public enum DisplayType {
        FLOWERS, NATURE;
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

        private final int[] mImageResources;

        ItemAdapter(int[] imageResources) {
            mImageResources = imageResources;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Picasso.with(holder.itemView.getContext())
                    .load(mImageResources[position])
                    .fit()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mImageResources.length;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;

            ItemViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }
}
