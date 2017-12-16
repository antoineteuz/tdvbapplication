package com.android.mytdvbapp.mytdvbapplication.network.auth;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public interface IApiAuthService {

    void login(Credentials credentials, Subscriber<Response<LoginResponse>> subscriber);
}
