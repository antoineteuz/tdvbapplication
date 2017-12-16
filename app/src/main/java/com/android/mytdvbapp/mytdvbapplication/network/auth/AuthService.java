package com.android.mytdvbapp.mytdvbapplication.network.auth;

import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.network.NetworkUtils;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceGenerator;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class AuthService implements IApiAuthService {

    private IRFAuthService service;

    public AuthService() {
        if (service == null) {
            service = ServiceGenerator.createService(IRFAuthService.class);
        }
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
        addObservable(service.login(credentials), subscriber);
    }
}
