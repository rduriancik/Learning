package com.example.robert.flickrlike.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.robert.flickrlike.db.PhotoDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by robert on 20.8.2017.
 */

@Table(database = PhotoDatabase.class)
public class Photo extends BaseModel implements Parcelable {

    @PrimaryKey
    private long id;

    @Column
    private String owner;

    @Column
    private String secret;

    @Column
    @SerializedName("server")
    private long serverId;

    @Column
    @SerializedName("farm")
    private long farmId;

    @Column
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SuppressLint("DefaultLocale")
    public String getPhotoUrl() {
        return String.format("https://farm%d.staticflickr.com/%d/%d_%s.jpg", farmId, serverId, id, secret);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.owner);
        dest.writeString(this.secret);
        dest.writeLong(this.serverId);
        dest.writeLong(this.farmId);
        dest.writeString(this.title);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.id = in.readLong();
        this.owner = in.readString();
        this.secret = in.readString();
        this.serverId = in.readLong();
        this.farmId = in.readLong();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
