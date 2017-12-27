package com.android.mytdvbapp.mytdvbapplication.network;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.FavoritesResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedActorsResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesDetailedEpisodeResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;

import java.util.HashMap;

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
 * Created by antoinepelletier on 16/12/2017.
 */

public class ServiceManager implements INetworkService {

    private static String TAG = "ServiceManager";
    private static final String URL_BACKEND = "https://api.thetvdb.com/";

    private Retrofit mRetrofit;

    public ServiceManager() {
        if (mRetrofit == null) {
            mRetrofit = getDefault();
        }
    }

    /**
     * Method to get default config retrofit
     *
     * @return retrofit instance
     */
    private Retrofit getDefault() {
        OkHttpClient okHttpClient = MainHttpClient.getInstance().getClient();
        String urlBackend = URL_BACKEND;
        return new Retrofit.Builder()
                .baseUrl(urlBackend)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static <IME> void addObservable(Observable<IME> observable, Subscriber<IME> subscriber) {
        NetworkUtils.getInstance().addSubscription(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }


    @Override
    public void login(Credentials credentials, Subscriber<Response<LoginResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.login(credentials), subscriber);
    }

    @Override
    public void refreshToken(String current_token, Subscriber<Response<LoginResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.refreshToken(current_token), subscriber);
    }

    @Override
    public void getFavorites(String current_token, Subscriber<Response<FavoritesResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getUserFavorites(current_token), subscriber);
    }

    @Override
    public void putFavorite(String id, String current_token, Subscriber<Response<FavoritesResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.putFavorite(id, current_token), subscriber);
    }

    @Override
    public void deleteFavorite(String id, String current_token, Subscriber<Response<FavoritesResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.deleteFavorite(id, current_token), subscriber);
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

    @Override
    public void getDetailsSerie(HashMap<String, String> headers, String id, Subscriber<Response<SerieDetailedResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getDetailsSerie(headers, id), subscriber);
    }

    @Override
    public void getActorsSerie(HashMap<String, String> headers, String id, Subscriber<Response<SerieDetailedActorsResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getActorsSerie(headers, id), subscriber);
    }

    @Override
    public void getEpisodesSerie(HashMap<String, String> headers, String id, Subscriber<Response<SeriesDetailedEpisodeResponse>> subscriber) {
        IRFApiService service = mRetrofit.create(IRFApiService.class);
        addObservable(service.getEpisodesSerie(headers, id), subscriber);
    }
}
