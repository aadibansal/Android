package com.example.aditya.movie_application;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    public final String BASE_URL = "https://api.themoviedb.org";
    RecyclerView recyclerView;
    MovieService movieService;
    MovieList movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        movieList = new MovieList(new ArrayList<MovieList.Result>());
        recyclerView = new RecyclerView(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.numColumns));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecroation(getResources().getDimensionPixelOffset(R.dimen.item_space), this));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.lightgrey));
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

        Call<MovieList> movieListCall = movieService.getMovieList(getResources().getString(R.string.api_key), getResources().getString(R.string.language), page);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                onDisplayMovieListInfo(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.fetching_movie_info_failure, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onDisplayMovieListInfo(MovieList movieLists) {
        Log.d("Movie", String.valueOf(movieLists.results.size()));
        movieList.results.addAll(movieLists.results);
        recyclerView.setAdapter(new MovieAdapter(movieList, this));
        setContentView(recyclerView);
    }

}
