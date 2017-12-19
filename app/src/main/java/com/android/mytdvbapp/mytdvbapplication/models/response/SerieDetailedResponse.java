package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.android.mytdvbapp.mytdvbapplication.models.SerieDetailsInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class SerieDetailedResponse {

    @SerializedName("data")
    @Expose
    private SerieDetailsInfo data;

    public SerieDetailsInfo getData() {
        return data;
    }

    public void setData(SerieDetailsInfo data) {
        this.data = data;
    }
}
