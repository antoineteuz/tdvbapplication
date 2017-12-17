package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class ActorInfo {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("seriesId")
    @Expose
    private int seriesId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("sortOrder")
    @Expose
    private int sortOrder;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageAuthor")
    @Expose
    private int imageAuthor;
    @SerializedName("imageAdded")
    @Expose
    private String imageAdded;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(int imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getImageAdded() {
        return imageAdded;
    }

    public void setImageAdded(String imageAdded) {
        this.imageAdded = imageAdded;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
