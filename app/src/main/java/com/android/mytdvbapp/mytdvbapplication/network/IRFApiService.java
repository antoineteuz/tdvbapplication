package com.android.mytdvbapp.mytdvbapplication.network;


import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.UserResponse;

import java.util.HashMap;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public interface IRFApiService {

    @POST("/login")
    Observable<Response<String>> login(@Body Credentials credentials);

    @GET("/user")
    Observable<Response<UserResponse>> getUser(@Header("Authorization") String token);

    @GET("/updated/query")
    Observable<Response<SeriesUpdatedResponse>> getLastSeriesUpdated(@HeaderMap HashMap<String, String> headers,
                                                              @Query("fromTime") String fromTime,
                                                              @Query("toTime") String toTime);
}
