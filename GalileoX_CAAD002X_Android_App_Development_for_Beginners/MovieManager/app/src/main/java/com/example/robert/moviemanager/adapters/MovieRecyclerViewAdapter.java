package com.example.robert.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robert.moviemanager.R;
import com.example.robert.moviemanager.activities.MovieDetailActivity;
import com.example.robert.moviemanager.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robert on 5.7.2017.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        Picasso.with(mContext)
                .load(movie.getPosterPath())
                .into(holder.ivMovieImage);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ivMovieImage)
        ImageView ivMovieImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.cvMovie)
        CardView cvMovie;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Movie movie = mMovies.get(getAdapterPosition());

            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(Movie.class.getSimpleName(), movie);
            mContext.startActivity(intent);
        }
    }
}
