package com.example.robert.flickrlike.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert on 20.8.2017.
 */

public class Photo {

    private long id;
    private String owner;
    private String secret;
    @SerializedName("server")
    private long serverId;
    @SerializedName("farm")
    private long farmId;
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
}
