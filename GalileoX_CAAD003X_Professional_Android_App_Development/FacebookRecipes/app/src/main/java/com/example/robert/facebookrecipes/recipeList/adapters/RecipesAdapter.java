package com.example.robert.facebookrecipes.recipeList.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robert on 18.8.2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipes;
    private ImageLoader imageLoader;
    private OnItemClickListener listener;

    public RecipesAdapter(List<Recipe> recipes, ImageLoader imageLoader, OnItemClickListener listener) {
        this.recipes = recipes;
        this.imageLoader = imageLoader;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_recipes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);

        imageLoader.load(holder.imgRecipe, currentRecipe.getImageURL());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.imgFav.setTag(currentRecipe.isFavorite());
        if (currentRecipe.isFavorite()) {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        holder.setOnItemClickListener(currentRecipe, listener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public void removeRecipes(Recipe recipe) {
        recipes.remove(recipe);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgRecipe)
        ImageView imgRecipe;
        @BindView(R.id.txtRecipeName)
        TextView txtRecipeName;
        @BindView(R.id.imgFav)
        ImageButton imgFav;
        @BindView(R.id.imgDelete)
        ImageButton imgDelete;
        @BindView(R.id.fbShare)
        ShareButton fbShare;
        @BindView(R.id.fbSend)
        SendButton fbSend;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnItemClickListener(final Recipe currentRecipe, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(currentRecipe);
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFavClick(currentRecipe);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(currentRecipe);
                }
            });

            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(currentRecipe.getSourceURL()))
                    .build();
            fbShare.setShareContent(content);
            fbSend.setShareContent(content);

        }
    }
}
