package com.example.robert.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robert.twitterclient.R;
import com.example.robert.twitterclient.entities.Image;
import com.example.robert.twitterclient.libs.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robert on 25.7.2017.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> dataset;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = dataset.get(position);
        holder.setOnClickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        imageLoader.load(holder.imgMedia, imageTweet.getImageURL());
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public void setItems(List<Image> newItems) {
        dataset.clear();
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTweet)
        TextView txtTweet;
        @BindView(R.id.imgMedia)
        ImageView imgMedia;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Image image, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}
