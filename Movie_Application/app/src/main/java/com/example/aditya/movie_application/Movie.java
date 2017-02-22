package com.example.aditya.movie_application;

/**
 * Created by Aditya on 2/19/2017.
 */

public class Movie {

    public Boolean adult;
    public String backdropPath;
    public Object belongsToCollection;
    public Integer budget;
    public String homepage;
    public Integer id;
    public String imdbId;
    public String originalLanguage;
    public String original_title;
    public String overview;
    public Double popularity;
    public String poster_path;
    public String release_date;
    public Integer revenue;
    public Integer runtime;
    public String status;
    public String tagline;
    public String title;
    public Boolean video;
    public float vote_average;
    public Integer vote_count;

    public Movie(Boolean adult, String backdropPath, Object belongsToCollection, Integer budget, String homepage, Integer id, String imdbId, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String releaseDate, Integer revenue, Integer runtime, String status, String tagline, String title, Boolean video, float voteAverage, Integer vote_count) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.original_title = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = voteAverage;
        this.vote_count = vote_count;
    }
}
