package com.android.mytdvbapp.mytdvbapplication.network.series;

import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;
import com.android.mytdvbapp.mytdvbapplication.network.NetworkUtils;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceGenerator;

import java.util.HashMap;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class GeneralService implements INetworkService {

    private IRFApiService service;

    public GeneralService() {
        if (service == null) {
            try {
                service = ServiceGenerator.createAuthService(IRFApiService.class, Session.get().getSessionToken());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
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
    public void getUser(Subscriber<Response<UserResponse>> subscriber) {
        addObservable(service.getUser(), subscriber);
    }

    @Override
    public void getLastSeriesUpdated(HashMap<String, String> headers, String fromTime, String toTime, Subscriber<Response<SeriesUpdatedResponse>> subscriber) {
        addObservable(service.getLastSeriesUpdated(headers, fromTime, toTime), subscriber);
    }
}
