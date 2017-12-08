package com.android.mytdvbapp.mytdvbapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DataBaseDefinition.DATABASE_NAME, null,
                DataBaseDefinition.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        for (Class<?> cls : DataBaseDefinition.Tables) {
            try {
                Method method = cls.getMethod(DataBaseDefinition.CREATE_SQL);
                String sql = (String) method.invoke(null); // static method
                if (sql != null) {
                    Log.i("DatabaseHelper", "onCreate");
                    db.execSQL(sql);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {

            try {
                db.execSQL("ALTER TABLE tbl_favorite ADD COLUMN c_favorite_favorite_status TEXT NOT NULL DEFAULT '1';");
            } catch (Exception e) {

            }
            try {
                db.execSQL("ALTER TABLE tbl_favorite ADD COLUMN c_favorite_is_synchronized TEXT NOT NULL DEFAULT '0';");
            } catch (Exception e) {

            }
            try {
                db.execSQL("ALTER TABLE tbl_favorite ADD COLUMN c_favorite_favorite_timestamp TEXT NOT NULL DEFAULT '0';");
            } catch (Exception e) {

            }
        }

    }
}