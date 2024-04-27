package com.avasaysayava.bagrutproject.database.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.avasaysayava.bagrutproject.database.DatabaseHelper;

public class DataSource {
    protected final DatabaseHelper databaseHelper;
    protected final Context context;
    protected SQLiteDatabase database;

    public DataSource(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.context = context;
    }

    public void openWriteable() {
        this.database = this.databaseHelper.getWritableDatabase();
    }

    public void openReadable() {
        this.database = this.databaseHelper.getReadableDatabase();
    }

    public void close() {
        this.databaseHelper.close();
    }
}
