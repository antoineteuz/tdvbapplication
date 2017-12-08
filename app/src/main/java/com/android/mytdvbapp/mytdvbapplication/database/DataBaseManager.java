package com.android.mytdvbapp.mytdvbapplication.database;

import android.content.Context;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class DataBaseManager {

    public static final String TAG = "DatabaseManager";

    private static DataBaseManager instance = null;
    private DataBaseHelper mDbHelper;
    private SeriesTableHelper instanceSeriesHelper;

    private DataBaseManager(Context context) {
        this.mDbHelper = new DataBaseHelper(context);
    }

    public static synchronized DataBaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseManager(context);
        }
        return instance;
    }

    public SeriesTableHelper getSeriesHelper() {
        if (instanceSeriesHelper == null) {
            instanceSeriesHelper = new SeriesTableHelper(mDbHelper);
        }
        return instanceSeriesHelper;
    }
}
