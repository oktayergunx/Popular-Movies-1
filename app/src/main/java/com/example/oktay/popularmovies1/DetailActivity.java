package com.example.oktay.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oktay.popularmovies1.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private String mMoviePoster;

    @BindView(R.id.iv_detail_movie_poster)
    ImageView mMoviePosterDisplay;
    private String mMovieTitle; //bunlar havada duruyor galiba, sil
    @BindView(R.id.tv_detail_title)
    TextView mMovieTitleDisplay;
    private String mMovieRate;
    @BindView(R.id.tv_detail_rate)
    TextView mMovieRateDisplay;
    private String mMovieRelease;
    @BindView(R.id.tv_detail_release_date)
    TextView mMovieReleaseDisplay;
    private String mMoviePlotSynopsis;
    @BindView(R.id.tv_plot_synopsis)
    TextView mMoviePlotSynopsisDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Movie movie = new Movie();
        String poster = movie.getPoster(); //?
        String title = movie.getTitle();
        String rate = movie.getRate();
        String release = movie.getRelease();
        String overview = movie.getOverview();

        mMovieTitleDisplay.setText(title);
        mMoviePlotSynopsisDisplay.setText(overview);
        mMovieRateDisplay.setText(rate);
        mMovieReleaseDisplay.setText(release);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
                mMoviePoster = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                Picasso.get()
                        .load(mMoviePoster)
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.image_not_found)
                        .into(mMoviePosterDisplay);
            }
        }
    }
}
