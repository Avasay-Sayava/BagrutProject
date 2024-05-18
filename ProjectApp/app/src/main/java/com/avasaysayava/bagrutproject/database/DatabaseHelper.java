package com.avasaysayava.bagrutproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + DatabaseContract.LevelsEntry.TABLE_NAME
            + " (" + DatabaseContract.LevelsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + DatabaseContract.LevelsEntry.COLUMN_TIME + " TIME NOT NULL,"
            + DatabaseContract.LevelsEntry.COLUMN_LEVEL + " TINYINT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatabaseContract.LevelsEntry.TABLE_NAME;

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