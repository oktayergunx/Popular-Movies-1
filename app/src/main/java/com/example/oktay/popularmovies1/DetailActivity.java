package com.example.oktay.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private String mMoviePoster;

    @BindView(R.id.iv_detail_movie_poster)
    ImageView mMoviePosterDisplay;
    private String mMovieTitle;
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
