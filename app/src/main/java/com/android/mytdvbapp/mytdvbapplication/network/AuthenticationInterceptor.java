package com.android.mytdvbapp.mytdvbapplication.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class AuthenticationInterceptor implements Interceptor {

    private static String TAG = "AuthenticationInterceptor";

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        Log.d(TAG, String.format("Interceptor request: %s", request.toString()));
        return chain.proceed(request);
    }
}
