package com.example.oktay.popularmovies1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oktay.popularmovies1.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_movie_names)
    TextView mMovieListTextView;
    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        makeTheMovieDbQuery();
    }

    private void makeTheMovieDbQuery() {
        String theMovieDbQueryType = query;
        URL theMovieDbQueryUrl = NetworkUtils.buildUrl(theMovieDbQueryType);
        mMovieListTextView.setText(theMovieDbQueryUrl.toString());
        new TheMovieDbQueryTask().execute(theMovieDbQueryUrl);
    }

    private void showJsonDataResults() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mMovieListTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mMovieListTextView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class TheMovieDbQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL queryUrl = urls[0];
            String theMovieDbQueryResults = null;
            try {
                theMovieDbQueryResults = NetworkUtils.getResponseFromHttpUrl(queryUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return theMovieDbQueryResults;
        }

        @Override
        protected void onPostExecute(String theMovieDbQueryResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (theMovieDbQueryResults != null && !theMovieDbQueryResults.equals("")) {
                showJsonDataResults();
                mMovieListTextView.setText(theMovieDbQueryResults);
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
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.popular) {
            Context context = MainActivity.this;
            String textToShow = "Popular Clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            // TODO 3 remove the toast message when you are done with it.
            query = "top_rated";
            makeTheMovieDbQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
