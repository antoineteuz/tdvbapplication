package com.android.mytdvbapp.mytdvbapplication.database;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class DataBaseDefinition {


    public static final String CREATE_SQL = "createTable";
    public static final String UPDATE_SQL = "updateTable";

    public static final String DATABASE_NAME = "tvdb.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * tables in database
     */
    public static final Class<?>[] Tables = {Series.class};
    private final static String CREATE_TABLE = "CREATE TABLE ";
    private final static String ALTER_TABLE = "ALTER TABLE ";
    private final static String ADD_COLUMN = " ADD COLUMN ";
    private final static String T_LONG = " LONG ";
    private final static String T_DOUBLE = " DOUBLE ";
    private final static String T_INTEGER = " INTEGER ";
    private final static String T_TEXT = " TEXT ";
    private final static String T_PRIMARY_KEY = " PRIMARY KEY  ";
    private final static String T_PRIMARY_KEY_AUTOINCREMENT = " PRIMARY KEY AUTOINCREMENT ";

    /**
     * table Favorite
     */
    public static final class Series {
        public static final String TABLE = "SERIES_TABLE";
        public static final String COLUMN_FAVORITE_UID = "c_favorite_uid";
        public static final String COLUMN_FAVORITE_DATE = "c_favorite_date";
        public static final String COLUMN_FAVORITE_DATE_CHANGE = "c_favorite_date_change";
        public static final String COLUMN_FAVORITE_FAVORITE_TYPE = "c_favorite_favorite_type";
        public static final String COLUMN_FAVORITE_FAVORITE_ID = "c_favorite_favorite_id";
        public static final String COLUMN_FAVORITE_FAVORITE_DATA = "c_favorite_favorite_data";
        public static final String COLUMN_FAVORITE_FAVORITE_URL = "c_favorite_favorite_url";
        public static final String COLUMN_FAVORITE_FAVORITE_STATUS = "c_favorite_favorite_status";
        public static final String COLUMN_FAVORITE_FAVORITE_TIMESTAMP = "c_favorite_favorite_timestamp";
        public static final String COLUMN_FAVORITE_IS_SYNC = "c_favorite_is_synchronized";

        /**
         * build a sql string to create table series
         */
        public static String createTable() {
            return CREATE_TABLE + TABLE + " ("
                    + COLUMN_FAVORITE_UID + T_INTEGER + T_PRIMARY_KEY_AUTOINCREMENT + ","
                    + COLUMN_FAVORITE_DATE + T_TEXT + ","
                    + COLUMN_FAVORITE_DATE_CHANGE + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_TYPE + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_ID + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_DATA + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_URL + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_STATUS + T_TEXT + ","
                    + COLUMN_FAVORITE_IS_SYNC + T_TEXT + ","
                    + COLUMN_FAVORITE_FAVORITE_TIMESTAMP + T_TEXT + ");";
        }

        /**
         * build a sql string to update table favorite
         */
        public static String deleteTable() {
            return "DROP TABLE IF EXISTS " + TABLE;
        }

    }
}