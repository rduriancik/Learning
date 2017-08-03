package com.example.robert.facebookrecipes.entities;

import com.example.robert.facebookrecipes.db.RecipesDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by robert on 3.8.2017.
 */

@Table(database = RecipesDatabase.class)
public class Recipe extends BaseModel {

    @SerializedName("recipe_id")
    @PrimaryKey
    private String recipeID;

    @Column
    private String title;

    @SerializedName("image_url")
    @Column
    private String imageURL;

    @SerializedName("source_url")
    @Column
    private String sourceURL;

    @Column
    private boolean favorite;

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Recipe) {
            Recipe recipe = (Recipe) obj;
            equals = this.recipeID.equals(recipe.getRecipeID());
        }

        return equals;
    }
}
