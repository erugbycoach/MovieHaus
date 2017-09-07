package com.example.android.moviemadness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.poster)
    ImageView imagePoster;

    @BindView(R.id.title)
    TextView detailsTitle;

    @BindView(R.id.backdrop)
    ImageView imageBackdrop;

    @BindView(R.id.release_date)
    TextView detailsReleaseDate;

    @BindView(R.id.overview)
    TextView detailsOverview;

    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle data = getIntent().getExtras();
        final Movie movie = data.getParcelable("movieDetails");
        setMovieDetails(movie);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(movie.getTitle());


    }

    private void setMovieDetails(Movie movie) {
        Picasso.with(imagePoster.getContext())
                .load(NetworkUtils.buildPosterUrl(movie.getPosterPath()))
                .into(imagePoster);
        Picasso.with(imageBackdrop.getContext())
                .load(NetworkUtils.buildBackdropPath(movie.getBackdropPath()))
                .into(imageBackdrop);
        detailsTitle.setText(movie.getTitle());
        detailsReleaseDate.setText(movie.getReleaseDate());
        detailsOverview.setText(movie.getOverview());
        ratingBar.setRating(movie.getVoteAverage());

    }

}
