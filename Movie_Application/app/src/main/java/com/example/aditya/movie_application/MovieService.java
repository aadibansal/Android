package com.example.aditya.movie_application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Aditya on 2/15/2017.
 */

public interface MovieService {

    @GET("3/movie/now_playing")
    Call<MovieList> getMovieList(@Query("api_key")String key, @Query("language")String language, @Query("page") Integer page);

    @GET("3/movie/{movieID}")
    Call<Movie> getMovieInfo(@Path("movieID")int movieId,@Query("api_key")String key);
}
