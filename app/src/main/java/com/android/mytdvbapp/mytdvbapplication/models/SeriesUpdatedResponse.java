package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class SeriesUpdatedResponse {

    @SerializedName("data")
    @Expose
    private List<SeriesInfo> data = null;

    public List<SeriesInfo> getData() {
        return data;
    }

    public void setData(List<SeriesInfo> data) {
        this.data = data;
    }
}
