package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class FavoritesResponse {

    @SerializedName("data")
    @Expose
    private FavoritesInfo data;

    public FavoritesInfo getData() {
        return data;
    }

    public void setData(FavoritesInfo data) {
        this.data = data;
    }
}
