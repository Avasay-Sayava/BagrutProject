package com.avasaysayava.bagrutproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

public class UUIDDataSource {
    private static final String PREFS_NAME = "Gwybie";
    private static final String UUID_PREF_NAME = "UUID";
    private final DatabaseHelper databaseHelper;
    private final Context context;
    private SQLiteDatabase database;

    public UUIDDataSource(Context context) {
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

    public void insertUUID(String uuid) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserEntry.COLUMN_UUID, uuid);
        this.database.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values);
    }

    public boolean UUIDExists(String uuid) {
        String selection = DatabaseContract.UserEntry.COLUMN_UUID + "=?";
        String[] args = {uuid};
        String[] columns = {DatabaseContract.UserEntry.COLUMN_UUID};
        Cursor cursor = this.database.query(DatabaseContract.UserEntry.TABLE_NAME, columns, selection, args, null, null, null);
        boolean out = cursor.getCount() > 0;
        cursor.close();
        return out;
    }

    public void saveUUID(String uuid) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(UUID_PREF_NAME, uuid);
        editor.apply();
    }

    public String getUUID() {
        SharedPreferences prefs = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(UUID_PREF_NAME, null);
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}