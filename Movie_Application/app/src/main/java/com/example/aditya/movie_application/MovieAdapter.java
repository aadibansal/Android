package com.example.aditya.movie_application;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Aditya on 2/16/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private final MovieList movieList;
    private Context context;

    MovieAdapter(MovieList movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        holder.movieNameBind.setText(movieList.results.get(position).original_title);
        holder.movieYearBind.setText(movieList.results.get(position).release_date.split("-")[0]);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w185"+movieList.results.get(position).poster_path)
                .fit()
                .into(holder.movieImageBind);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                context.startActivity(MovieDetailActivity.getIntent(context, movieList.results.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.results.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        final ImageView movieImageBind;
        final TextView movieNameBind;
        final TextView movieYearBind;

        ItemViewHolder(View view) {
            super(view);
            movieImageBind = (ImageView) view.findViewById(R.id.movieImage);
            movieNameBind = (TextView) view.findViewById(R.id.movieName);
            movieYearBind = (TextView) view.findViewById(R.id.movieYear);
        }
    }
}

