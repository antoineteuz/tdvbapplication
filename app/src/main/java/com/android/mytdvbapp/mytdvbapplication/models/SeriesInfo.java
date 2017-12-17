package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class SeriesInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
