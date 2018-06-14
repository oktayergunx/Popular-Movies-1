package com.example.oktay.popularmovies1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_detail_movie_poster)
    ImageView mMoviePosterDisplay;
    @BindView(R.id.tv_detail_title)
    TextView mMovieTitleDisplay;
    @BindView(R.id.tv_detail_rate)
    TextView mMovieRateDisplay;
    @BindView(R.id.tv_detail_release_date)
    TextView mMovieReleaseDisplay;
    @BindView(R.id.tv_plot_synopsis)
    TextView mMoviePlotSynopsisDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        // https://stackoverflow.com/questions/41791737/how-to-pass-json-image-from-recycler-view-to-another-activity
        String poster = getIntent().getStringExtra("poster");
        String title = getIntent().getStringExtra("title");
        String rate = getIntent().getStringExtra("rate");
        String release = getIntent().getStringExtra("release");
        String overview = getIntent().getStringExtra("overview");

        mMovieTitleDisplay.setText(title);
        mMoviePlotSynopsisDisplay.setText(overview);
        mMovieRateDisplay.setText(rate);
        mMovieReleaseDisplay.setText(release);
        Picasso.get()
                .load(poster)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_found)
                .into(mMoviePosterDisplay);

    }
}
