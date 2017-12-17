package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.android.mytdvbapp.mytdvbapplication.models.EpisodeInfo;
import com.android.mytdvbapp.mytdvbapplication.models.Links;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class SeriesDetailedEpisodeResponse {

    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("data")
    @Expose
    private List<EpisodeInfo> data = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<EpisodeInfo> getData() {
        return data;
    }

    public void setData(List<EpisodeInfo> data) {
        this.data = data;
    }
}
