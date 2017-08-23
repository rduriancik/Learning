package com.example.robert.flickrlike.likedPhotosActivity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.robert.flickrlike.R;
import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosAdapter extends RecyclerView.Adapter<LikedPhotosAdapter.ViewHolder> {

    private List<Photo> photos;
    private ImageLoader imageLoader;

    public LikedPhotosAdapter(List<Photo> photos, ImageLoader loader) {
        this.photos = photos;
        this.imageLoader = loader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        imageLoader.load(holder.image, photos.get(position).getPhotoUrl());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
