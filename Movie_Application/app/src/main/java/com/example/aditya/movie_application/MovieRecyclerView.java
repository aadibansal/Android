package com.example.aditya.movie_application;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aditya on 2/21/2017.
 */

public class MovieRecyclerView {


    public final String BASE_URL = "https://api.themoviedb.org";
    RecyclerView recyclerView;
    MovieService movieService;
    MovieList movieList;
    Context context;

    MovieRecyclerView(Context context){
        this.context = context;
        initViews();
    }

    private void initViews(){
        movieList = new MovieList(new ArrayList<MovieList.Result>());
        recyclerView = new RecyclerView(context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, this.context.getResources().getInteger(R.integer.numColumns));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecroation(context.getResources().getDimensionPixelOffset(R.dimen.item_space), context));
        recyclerView.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
        loadMovieList(1);
    }

    private void loadMovieList(Integer page){
        Log.d("Page", String.valueOf(page));
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieList> movieListCall = movieService.getMovieList(context.getResources().getString(R.string.api_key), context.getResources().getString(R.string.language), page);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                onDisplayMovieListInfo(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(context, R.string.fetching_movie_info_failure, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onDisplayMovieListInfo(MovieList movieLists) {
        Log.d("Movie", String.valueOf(movieLists.results.size()));
        movieList.results.addAll(movieLists.results);
        recyclerView.setAdapter(new MovieAdapter(movieList, context));
        //setContentView(recyclerView);
    }
}
