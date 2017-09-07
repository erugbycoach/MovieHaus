package com.example.android.moviemadness;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by William D Howell on 9/5/2017.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getCanonicalName();

   /* public static URL buildUrl(String apiKey, String sortType) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(sortType)
                .appendQueryParameter("api_key", apiKey);
        URL url = null;

        try {
            url = new URL(builder.build().toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
         return url;
    }
    */

    public static URL buildUrl(String apiKey, String filterType) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(filterType)
                .appendQueryParameter("api_key", apiKey);
        URL url = null;
        try {
            url = new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL moviesRequestUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) moviesRequestUrl.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String buildPosterUrl(String posterPath) {
        return "http://image.tmdb.org/t/p/w500/" + posterPath;
    }

    public static String buildBackdropPath(String backdropPath) {
        return "http://image.tmdb.org/t/p/w500/" + backdropPath;
    }

}
