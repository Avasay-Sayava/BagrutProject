package com.avasaysayava.bagrutproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.UserEntry.TABLE_NAME + " (" +
                    DatabaseContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    DatabaseContract.UserEntry.COLUMN_UUID + " CHAR(36) NOT NULL," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL1 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL2 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL3 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL4 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL5 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL6 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL7 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL8 + " TIME," +
                    DatabaseContract.UserEntry.COLUMN_LEVEL9 + " TIME)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.UserEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}