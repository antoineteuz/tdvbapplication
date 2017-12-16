package com.android.mytdvbapp.mytdvbapplication.network;

import android.util.Log;

import com.android.mytdvbapp.mytdvbapplication.models.SessionToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";

    private static final String URL_BACKEND = "https://api.thetvdb.com/";

    private static Gson gson;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit defaultRetrofit = getDefault();
    private static Retrofit authRetrofit;

    public static Retrofit getDefault() {
        if (defaultRetrofit == null) {
            defaultRetrofit = new Retrofit.Builder()
                    .baseUrl(URL_BACKEND)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(httpClient.build())
                    .build();
        }
        return defaultRetrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getDefault().create(serviceClass);
    }

    /**
     * With this, you don't have to pass the auth token to every API call you make
     *
     * @param serviceClass The "IRF" interface service class
     * @param authToken    The auth token
     * @param <S>          The service class
     * @return
     */
    public static <S> S createAuthService(Class<S> serviceClass, final SessionToken authToken) {
        if (authToken != null && authToken.validateToken()) {
            Log.d(TAG, String.format("Valid token %s", authToken.getToken()));
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken.getToken());

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.interceptors().add(interceptor);

                Log.d("ServiceGenerator", "Added interceptor !");
                if (authRetrofit == null) {
                    authRetrofit = new Retrofit.Builder()
                            .baseUrl(URL_BACKEND)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(getGson()))
                            .client(httpClient.build())
                            .build();
                }
            }
        }

        return authRetrofit.create(serviceClass);
    }

    private static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
}
