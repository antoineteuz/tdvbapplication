package com.android.mytdvbapp.mytdvbapplication.network;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;

import java.util.HashMap;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public interface INetworkService {

    void login(Credentials credentials, Subscriber<Response<LoginResponse>> subscriber);

    void getUser(String token, Subscriber<Response<UserResponse>> subscriber);

    void getLastSeriesUpdated(HashMap<String, String> headers, String fromTime, String toTime, Subscriber<Response<SeriesUpdatedResponse>> subscriber);

}
