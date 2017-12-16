package com.android.mytdvbapp.mytdvbapplication.network.auth;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public interface IRFAuthService {

    @POST("/login")
    Observable<Response<LoginResponse>> login(@Body Credentials credentials);
}
