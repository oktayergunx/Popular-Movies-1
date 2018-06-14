package com.example.oktay.popularmovies1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oktay.popularmovies1.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
//I went through Sunshine's RecyclerView and Click Handlers.
//Following codes are from Sunshine.
    private Movie[] mMovieData;
    private final MovieAdapterOnClickHandler mClickHandler;

    public MovieAdapter(Movie[] movie, MovieAdapterOnClickHandler clickHandler) {
        mMovieData = movie;
        mClickHandler = clickHandler;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(int adapterPosition);
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMovieListImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieListImageView = (ImageView) itemView.findViewById(R.id.iv_movie_posters);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        //inflate list item xml into a view
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        //set the movie for list item's position
        String movieToBind = mMovieData[position].getPoster();
        Picasso.get()
                .load(movieToBind)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_found)
                .into(holder.mMovieListImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) {
            return 0;
        }
        return mMovieData.length;
    }
}
