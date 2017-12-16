package com.android.mytdvbapp.mytdvbapplication.models;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class Session {

    private static String TAG = "Session";

    public static final String LOCAL_TOKEN = "sessionToken";

    private static Session instance = null;
    private Context context;
    private SharedPreferences sp;

    private SessionToken sessionToken = null;

    private Session(Context context, @Nullable SessionToken sessionToken) {
        this.context = context;
        sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        if (sessionToken == null) {
            loadFromDisk();
        } else {
            this.sessionToken = sessionToken;
        }
    }

    public static Session start(Context context, @Nullable SessionToken sessionToken) {
        if (instance == null || instance.sessionToken == null) {
            instance = new Session(context, sessionToken);
        }
        return instance;
    }

    public static Session get() throws ServiceException {
        if (instance == null) {
            throw new ServiceException(401);
        }
        return instance;
    }

    private void loadFromDisk() {

        String tmpToken = sp.getString(LOCAL_TOKEN, null);
        if (tmpToken != null) {
            setSessionToken(new SessionToken(tmpToken));
        }
    }

    private void save() {
        SharedPreferences.Editor spEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        spEdit.putString(LOCAL_TOKEN, this.sessionToken.getToken()).apply();
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
        save();
        Log.d(TAG, sessionToken.toString());

    }
}
