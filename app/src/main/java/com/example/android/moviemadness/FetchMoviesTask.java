package com.example.android.moviemadness;

import android.os.AsyncTask;

import java.net.URL;
import java.util.List;

/**
 * Created by William D Howell on 9/5/2017.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

    private MoviesAdapter adapter;

    public FetchMoviesTask(MoviesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        URL moviesRequestURL = NetworkUtils.buildUrl(Movie.API_KEY, params[0]);
        try {
            String jsonMovieResponse = NetworkUtils
                    .getResponseFromHttpUrl(moviesRequestURL);
            MovieCollection movieCollection = MoviesJsonUtils.parseJson(jsonMovieResponse);
            return movieCollection.getMovies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies != null) {
            adapter.setMoviesData(movies);
        }
    }
}
