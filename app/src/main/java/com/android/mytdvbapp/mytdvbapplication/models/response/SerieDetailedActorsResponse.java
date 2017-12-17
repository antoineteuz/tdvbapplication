package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.android.mytdvbapp.mytdvbapplication.models.ActorInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class SerieDetailedActorsResponse {

    @SerializedName("data")
    @Expose
    private List<ActorInfo> data = null;

    public List<ActorInfo> getData() {
        return data;
    }

    public void setData(List<ActorInfo> data) {
        this.data = data;
    }
}
