package com.android.mytdvbapp.mytdvbapplication.base;

import android.app.Application;

import com.android.mytdvbapp.mytdvbapplication.models.Session;

import io.realm.Realm;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Session.start(getApplicationContext(), null);
    }
}
