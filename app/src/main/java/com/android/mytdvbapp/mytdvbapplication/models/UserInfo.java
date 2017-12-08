package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class UserInfo {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("favoritesDisplaymode")
    @Expose
    private String favoritesDisplaymode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFavoritesDisplaymode() {
        return favoritesDisplaymode;
    }

    public void setFavoritesDisplaymode(String favoritesDisplaymode) {
        this.favoritesDisplaymode = favoritesDisplaymode;
    }
}
