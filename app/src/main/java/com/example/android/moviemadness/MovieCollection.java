package com.example.android.moviemadness;

import java.util.List;

/**
 * Created by William D Howell on 9/5/2017.
 */

public class MovieCollection {

    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieCollection{" +
                "movies=" + movies +
                '}';
    }
}
