package com.android.mytdvbapp.mytdvbapplication.database;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class SeriesTableHelper {

    private DataBaseHelper mDbHelper;

    public SeriesTableHelper(DataBaseHelper dbHelper) {
        mDbHelper = dbHelper;
    }

//    /**
//     * save a tv show into the Sqlite DB
//     *
//     * @param tvShowModel
//     */
//    public boolean insertTVShow(TVShowModel tvShowModel) {
//
//        SQLiteDatabase db = null;
//        synchronized (this) {
//            try {
//                db = mDbHelper.getWritableDatabase();
//                try {
//
//                    db.beginTransaction();
//                    ContentValues values = new ContentValues();
//
//                    values.put(
//                            DataBaseDefinition.Series.COLUMN_FAVORITE_DATE,
//                            tvShowModel.getDate());
//                    values.put(
//                            DataBaseDefinition.Series.COLUMN_FAVORITE_DATE_CHANGE,
//                            tvShowModel.getDateChange());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_TYPE,
//                            tvShowModel.getFavoriteType());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_ID,
//                            tvShowModel.getFavoriteId());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_DATA,
//                            tvShowModel.getFavoriteData());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_URL,
//                            tvShowModel.getFavoriteUrl());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_STATUS,
//                            tvShowModel.getStatus());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_TIMESTAMP,
//                            tvShowModel.getTimestamp());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_IS_SYNC,
//                            tvShowModel.getIsSync());
//
//                    db.insertOrThrow(DatabaseDefinition.Series.TABLE, null,
//                            values);
//                    db.setTransactionSuccessful();
//                    Timber.i(DatabaseManager.TAG + " : " + "Saved favorite: " + "UID: "
//                            + tvShowModel.getUid() + " - Favorite Type: "
//                            + tvShowModel.getFavoriteType());
//                } catch (SQLException e) {
//
//                    e.printStackTrace();
//                    Timber.i(DatabaseManager.TAG + " : " + "SQLException: " + e.getMessage());
//                    return false;
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                    Timber.i(DatabaseManager.TAG + " : " + "Exception" + ": " + e.getMessage());
//                    return false;
//                } finally {
//                    if (db.isOpen())
//                        db.endTransaction();
//                }
//            } finally {
//                if (db != null) {
//                    db.close();
//                }
//            }
//        }
//        return true;
//    }
//
//
//    public boolean updateTVShow(TVShowModel tvShowModel) {
//
//        SQLiteDatabase db = null;
//        synchronized (this) {
//            try {
//                db = mDbHelper.getWritableDatabase();
//                try {
//
//                    db.beginTransaction();
//                    ContentValues values = new ContentValues();
//
//                    values.put(DataBaseDefinition.Series.COLUMN_FAVORITE_UID,
//                            tvShowModel.getUid());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_DATE_CHANGE,
//                            tvShowModel.getDateChange());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_TYPE,
//                            tvShowModel.getFavoriteType());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_ID,
//                            tvShowModel.getFavoriteId());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_DATA,
//                            tvShowModel.getFavoriteData());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_URL,
//                            tvShowModel.getFavoriteUrl());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_STATUS,
//                            tvShowModel.getStatus());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_FAVORITE_TIMESTAMP,
//                            tvShowModel.getTimestamp());
//                    values.put(
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_IS_SYNC,
//                            tvShowModel.getIsSync());
//
//                    db.update(DatabaseDefinition.Series.TABLE, values,
//                            DatabaseDefinition.Series.COLUMN_FAVORITE_UID
//                                    + "=" + tvShowModel.getUid(), null);
//                    db.setTransactionSuccessful();
//                    Log.d(DatabaseManager.TAG, " : " + "Update favorite: " + "UID: "
//                            + tvShowModel.getUid() + " - Favorite Type: "
//                            + tvShowModel.getFavoriteType());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    Log.d(DatabaseManager.TAG, " : " + "SQLException: " + e.getMessage());
//                    return false;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d(DatabaseManager.TAG, " : " + "Exception" + ": " + e.getMessage());
//                    return false;
//                } finally {
//                    db.endTransaction();
//                }
//            } finally {
//                if (db != null) {
//                    db.close();
//                }
//            }
//        }
//        return true;
//    }
//
//
//    public boolean deleteAllTVShow(Context context) {
//        SQLiteDatabase db = null;
//        synchronized (this) {
//            try {
//                db = mDbHelper.getWritableDatabase();
//                try {
//
//                    db.beginTransaction();
//
//                    db.delete(
//                            DatabaseDefinition.Favorite.TABLE,
//                            DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TYPE
//                                    + "=? ", new String[]{typeFavorite});
//
//                    db.setTransactionSuccessful();
//                    Timber.i(DatabaseManager.TAG + " : " + "Removed favorite type: "
//                            + typeFavorite);
//
//                    return true;
//                } catch (SQLException e) {
//
//                    e.printStackTrace();
//                    Timber.i(DatabaseManager.TAG + " : " + "SQLException: " + e.getMessage());
//                    return false;
//                } finally {
//                    db.endTransaction();
//                }
//            } finally {
//                if (db != null) {
//                    db.close();
//                }
//            }
//        }
//    }
//
//    public List<FavoriteModel> getFavorites(String typeFav) {
//
//        List<FavoriteModel> lstFavorite = new ArrayList<>();
//
//        String selectQuery = "SELECT * FROM "
//                + DatabaseDefinition.Favorite.TABLE + " WHERE  "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TYPE
//                + "=?"
//                + " ORDER BY " + DatabaseDefinition.Favorite.COLUMN_FAVORITE_UID + " DESC ";
//        SQLiteDatabase db = null;
//        Cursor c = null;
//
//        try {
//            db = mDbHelper.getReadableDatabase();
//            c = db.rawQuery(selectQuery, new String[]{typeFav});
//            lstFavorite = parseFavoriteList(c);
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//            if (c != null) {
//                c.close();
//            }
//        }
//
//        return lstFavorite;
//    }
//
//    public List<FavoriteModel> getFavoritesOfTop(String typeFav, int top) {
//
//        List<FavoriteModel> lstFavorite = new ArrayList<FavoriteModel>();
//
//        String selectQuery = "SELECT * FROM "
//                + DatabaseDefinition.Favorite.TABLE + " WHERE  "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TYPE
//                + "=? AND "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_STATUS
//                + "= 1"
//                + " ORDER BY " + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TIMESTAMP + " DESC "
//                + (top > 10 ? "" : " LIMIT " + String.valueOf(top));
//
//        SQLiteDatabase db = null;
//        Cursor c = null;
//
//        try {
//            db = mDbHelper.getReadableDatabase();
//            c = db.rawQuery(selectQuery, new String[]{typeFav});
//            lstFavorite = parseFavoriteList(c);
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//            if (c != null) {
//                c.close();
//            }
//        }
//
//        return lstFavorite;
//    }
//
//    public FavoriteModel getFavorite(String favID) {
//
//        FavoriteModel favorite = null;
//
//        String selectQuery = "SELECT * FROM "
//                + DatabaseDefinition.Favorite.TABLE + " WHERE  "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_ID
//                + "=?";
//        SQLiteDatabase db = null;
//        Cursor c = null;
//
//        try {
//            db = mDbHelper.getReadableDatabase();
//            c = db.rawQuery(selectQuery, new String[]{favID});
//            List<FavoriteModel> lstFavorite = parseFavoriteList(c);
//            if (lstFavorite.size() > 0)
//                favorite = lstFavorite.get(0);
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//            if (c != null) {
//                c.close();
//            }
//        }
//
//        return favorite;
//    }
//
//    private List<FavoriteModel> parseFavoriteList(Cursor c) {
//        List<FavoriteModel> lstFavorite = new ArrayList<FavoriteModel>();
//        if (c != null && c.moveToFirst()) {
//            do {
//
//                FavoriteModel favoriteModel = new FavoriteModel();
//                favoriteModel
//                        .setUid(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_UID)));
//                favoriteModel
//                        .setDate(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_DATE)));
//                favoriteModel
//                        .setDateChange(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_DATE_CHANGE)));
//                favoriteModel
//                        .setFavoriteType(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TYPE)));
//                favoriteModel
//                        .setFavoriteId(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_ID)));
//                favoriteModel
//                        .setFavoriteData(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_DATA)));
//                favoriteModel
//                        .setFavoriteUrl(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_URL)));
//                favoriteModel
//                        .setStatus(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_STATUS)));
//                favoriteModel
//                        .setTimestamp(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TIMESTAMP)));
//                favoriteModel
//                        .setIsSync(c.getString(c
//                                .getColumnIndex(DatabaseDefinition.Favorite.COLUMN_FAVORITE_IS_SYNC)));
//                // Adding favorite to list
//                lstFavorite.add(favoriteModel);
//
//            } while (c.moveToNext());
//        }
//        return lstFavorite;
//    }
//
//    public List<FavoriteModel> getSynchronizedFavorites(String typeFav) {
//        List<FavoriteModel> lstFavorite = new ArrayList<>();
//
//        String selectQuery = "SELECT * FROM "
//                + DatabaseDefinition.Favorite.TABLE + " WHERE  "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_TYPE
//                + "=?"
//                + " AND "
//                + DatabaseDefinition.Favorite.COLUMN_FAVORITE_FAVORITE_STATUS
//                + " !=0 "
//                + " ORDER BY " + DatabaseDefinition.Favorite.COLUMN_FAVORITE_UID + " DESC ";
//        SQLiteDatabase db = null;
//        Cursor c = null;
//
//        try {
//            db = mDbHelper.getReadableDatabase();
//            c = db.rawQuery(selectQuery, new String[]{typeFav});
//            lstFavorite = parseFavoriteList(c);
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//            if (c != null) {
//                c.close();
//            }
//        }
//
//        return lstFavorite;
//    }
}
