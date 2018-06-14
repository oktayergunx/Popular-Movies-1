package com.example.oktay.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oktay.popularmovies1.model.Movie;
import com.example.oktay.popularmovies1.utilities.NetworkUtils;
import com.example.oktay.popularmovies1.utilities.TheMovieDbJsonUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private Movie[] jsonMovieData;

    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);

        ButterKnife.bind(this);

        //how many columns can fit in one page. this method explained below.
        int mNoOfColumns = calculateNoOfColumns(getApplicationContext());

        GridLayoutManager layoutManager = new GridLayoutManager(this, mNoOfColumns);
        //set the layout manager
        mRecyclerView.setLayoutManager(layoutManager);
        //changes in content shouldn't change the layout size
        mRecyclerView.setHasFixedSize(true);

        //set movie adapter for recycler view
        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();
    }

    private void loadMovieData() {
        String theMovieDbQueryType = query;
        showJsonDataResults();
        new FetchMovieTask().execute(theMovieDbQueryType);
    }

    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class destinationClass = DetailActivity.class;

        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, adapterPosition);
        intentToStartDetailActivity.putExtra("title", jsonMovieData[adapterPosition].getTitle());
        intentToStartDetailActivity.putExtra("poster", jsonMovieData[adapterPosition].getPoster());
        intentToStartDetailActivity.putExtra("rate", jsonMovieData[adapterPosition].getRate());
        intentToStartDetailActivity.putExtra("release", jsonMovieData[adapterPosition].getRelease());
        intentToStartDetailActivity.putExtra("overview", jsonMovieData[adapterPosition].getOverview());

        startActivity(intentToStartDetailActivity);
    }

    private void showJsonDataResults() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if (params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(sortBy);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                jsonMovieData
                        = TheMovieDbJsonUtils.getMovieInformationsFromJson(MainActivity.this, jsonMovieResponse);

                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showJsonDataResults();
                //mMovieAdapter.setMovieData(movieData);
                mMovieAdapter = new MovieAdapter(movieData,MainActivity.this);
                mRecyclerView.setAdapter(mMovieAdapter);
            } else {
                showErrorMessage();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();

        if (menuItemSelected == R.id.action_popular) {
            query = "popular";
            loadMovieData();
            return true;
        }

        if (menuItemSelected == R.id.action_top_rated) {
            query = "top_rated";
            loadMovieData();
            return true;
        }

        if(menuItemSelected == R.id.action_about){
            Intent startAboutActivity = new Intent(this, AboutActivity.class);
            startActivity(startAboutActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //calculates how many columns can I fit in screen.
    //Source: https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

}
