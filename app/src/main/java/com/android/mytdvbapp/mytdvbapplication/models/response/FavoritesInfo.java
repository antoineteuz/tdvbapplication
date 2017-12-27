package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class FavoritesInfo {

    @SerializedName("favorites")
    @Expose
    private List<String> favorites = null;

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }
}
