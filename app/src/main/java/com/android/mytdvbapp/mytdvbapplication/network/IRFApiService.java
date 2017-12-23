package com.android.mytdvbapp.mytdvbapplication.network;


import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedActorsResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesDetailedEpisodeResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;

import java.util.HashMap;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public interface IRFApiService {

    @POST("/login")
    Observable<Response<LoginResponse>> login(@Body Credentials credentials);

    @GET("/refresh_token")
    Observable<Response<LoginResponse>> refreshToken(@Header("Authorization") String token);

    @GET("/user")
    Observable<Response<UserResponse>> getUser(@Header("Authorization") String token);

    @GET("/updated/query")
    Observable<Response<SeriesUpdatedResponse>> getLastSeriesUpdated(@HeaderMap HashMap<String, String> headers,
                                                                     @Query("fromTime") String fromTime,
                                                                     @Query("toTime") String toTime);

    @GET("/series/{id}")
    Observable<Response<SerieDetailedResponse>> getDetailsSerie(@HeaderMap HashMap<String, String> headers,
                                                                @Path("id") String id);

    @GET("/series/{id}/episodes")
    Observable<Response<SeriesDetailedEpisodeResponse>> getEpisodesSerie(@HeaderMap HashMap<String, String> headers,
                                                                         @Path("id") String id);

    @GET("/series/{id}/actors")
    Observable<Response<SerieDetailedActorsResponse>> getActorsSerie(@HeaderMap HashMap<String, String> headers,
                                                                     @Path("id") String id);

    //TODO : implement this one
    @GET("/series/{id}/images")
    Observable<Response<SerieDetailedResponse>> getImagesSerie(@HeaderMap HashMap<String, String> headers,
                                                               @Path("id") String id);


}
