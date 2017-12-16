package com.android.mytdvbapp.mytdvbapplication.models;

import com.android.mytdvbapp.mytdvbapplication.helper.Constants;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class Credentials {

    private String apiKey;
    private String username;
    private String userkey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public Credentials(String apiKey, String username, String userkey) {
        this.apiKey = apiKey;
        this.username = username;
        this.userkey = userkey;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "apiKey='" + apiKey + '\'' +
                ", username='" + username + '\'' +
                ", userkey='" + userkey + '\'' +
                '}';
    }
}