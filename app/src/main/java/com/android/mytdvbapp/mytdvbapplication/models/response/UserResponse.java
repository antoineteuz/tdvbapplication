package com.android.mytdvbapp.mytdvbapplication.models.response;

import com.android.mytdvbapp.mytdvbapplication.models.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class UserResponse {

    @SerializedName("data")
    @Expose
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
