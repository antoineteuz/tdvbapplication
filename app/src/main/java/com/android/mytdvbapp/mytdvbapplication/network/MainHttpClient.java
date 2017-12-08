package com.android.mytdvbapp.mytdvbapplication.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class MainHttpClient {

    private static final long DEFAULT_TIMEOUT_SECONDS = 60;
    private static MainHttpClient sInstance;
    private OkHttpClient okHttpClient;


    public synchronized static MainHttpClient getInstance() {
        if (sInstance == null) {
            sInstance = new MainHttpClient();
        }
        return sInstance;
    }

    private MainHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .build();
    }


    /**
     * build a okHttp client to intercept , and handle timeout request
     * and intercept cookie or add cookies
     *
     * @return okHttpClient
     */
    public OkHttpClient getClient() {
        return okHttpClient;
    }


}