package com.android.mytdvbapp.mytdvbapplication.network.series;


import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;

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

    @GET("/user")
    Observable<Response<UserResponse>> getUser();

    @GET("/updated/query")
    Observable<Response<SeriesUpdatedResponse>> getLastSeriesUpdated(@HeaderMap HashMap<String, String> headers,
                                                                     @Query("fromTime") String fromTime,
                                                                     @Query("toTime") String toTime);
}
