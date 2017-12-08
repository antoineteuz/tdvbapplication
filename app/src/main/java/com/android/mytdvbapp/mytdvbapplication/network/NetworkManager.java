package com.android.mytdvbapp.mytdvbapplication.network;

import android.content.Context;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.UserResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class NetworkManager implements INetworkService {

    private Retrofit mRetrofit;

    public NetworkManager(Context context) {
        if (mRetrofit == null) {
            mRetrofit = getService(context);
        }
    }

    private static final String URL_BACKEND = "https://api.thetvdb.com/";

    private static <IME> void addObservable(Observable<IME> observable, Subscriber<IME> subscriber) {
        NetworkUtils.getInstance().addSubscription(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }


    private Retrofit getService(Context context) {
        OkHttpClient okHttpClient = MainHttpClient.getInstance().getClient();
        String urlBackend = URL_BACKEND;
        return new Retrofit.Builder()
                .baseUrl(urlBackend)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Override
    public void login(Credentials credentials, Subscriber<Response<UserResponse>> subscriber) {
    }

    @Override
    public void getUser(String token, Subscriber<Response<UserResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getUser(token), subscriber);
    }

    @Override
    public void getLastSeriesUpdated(HashMap<String, String> headers, String fromTime, String toTime, Subscriber<Response<SeriesUpdatedResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getLastSeriesUpdated(headers, fromTime, toTime), subscriber);
    }
}
