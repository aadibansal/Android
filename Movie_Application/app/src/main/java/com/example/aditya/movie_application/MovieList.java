package com.example.aditya.movie_application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 2/15/2017.
 */

public class MovieList {

    public List<Result> results;

    public MovieList(List<Result> results){
        this.results = results;
    }

    class Result {
        public String poster_path;
        public String release_date;
        public String original_title;
        public Integer id;

        public Result(String posterPath, String releaseDate, String originalTitle, Integer id) {
            this.poster_path = posterPath;
            this.release_date = releaseDate;
            this.original_title = originalTitle;
            this.id = id;
        }
    }
}
