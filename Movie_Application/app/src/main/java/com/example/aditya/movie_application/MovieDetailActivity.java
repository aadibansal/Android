package com.example.aditya.movie_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.BinderThread;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieDetailActivity extends AppCompatActivity {

    private static final String ARG_SELECTED_MOVIE_ID= "movieID";
    public final String BASE_URL = "https://api.themoviedb.org";

    @BindView(R.id.activity_movie_detail)
    RelativeLayout movieDetailLayout;
    @BindView(R.id.movieTitleName)
    TextView movieName;
    @BindView(R.id.movieReleaseYear)
    TextView movieReleaseYear;
    @BindView(R.id.movieHomePage)
    TextView movieHomePage;
    @BindView(R.id.movieLength)
    TextView movieLength;
    @BindView(R.id.movieRating)
    RatingBar movieRating;
    @BindView(R.id.movieVoteCount)
    TextView movieVoteCount;

    public static Intent getIntent(Context context, Integer movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(ARG_SELECTED_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Integer movieID = getIntent().getIntExtra(ARG_SELECTED_MOVIE_ID, 14564);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<Movie> movieCall = movieService.getMovieInfo(movieID, getResources().getString(R.string.api_key));

        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                    displayMovie(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, R.string.fetching_movie_info_failure, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayMovie(Movie movie){
        movieName.setText(movie.original_title);
        movieReleaseYear.setText(movie.release_date);
        movieLength.setText(timeConvert(movie.runtime));
        movieHomePage.setText(movie.homepage);
        movieRating.setRating(movie.vote_average);
        movieVoteCount.setText(String.valueOf(movie.vote_count));
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w185"+movie.poster_path)
                .into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                movieDetailLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private String timeConvert(int time) {
        return String.format("%d:%02d", TimeUnit.MINUTES.toHours(time), TimeUnit.MINUTES.toMinutes(time%60));
    }
}
