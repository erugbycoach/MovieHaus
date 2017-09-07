package com.example.android.moviemadness;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by William D Howell on 9/5/2017.
 */

public class Movie implements Parcelable {

    private String title;
    private String backdropPath;
    private String posterPath;
    private String releaseDate;
    private long voteAverage;
    private String overview;
    private int id;
    public static String API_KEY = "406c50e1a9a1c2ea10515b48e299a1e9";

    public Movie() {
        // Required Blank Constructor
    }

    // Read data for parcel

    public Movie(Parcel in) {

        title = in.readString();
        backdropPath = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readLong();
        overview = in.readString();
        id = in.readInt();
    }

    public static Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Create Getter and Setter methods

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(long voteAverage) {
        this.voteAverage = voteAverage;
    }

    // Create describeContents method required by Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    //Write data into parcel for later use



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeLong(voteAverage);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}
