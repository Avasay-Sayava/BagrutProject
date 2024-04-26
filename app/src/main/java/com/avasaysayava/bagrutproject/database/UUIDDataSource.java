package com.avasaysayava.bagrutproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.UUID;

public class UUIDDataSource {
    private static final String PREFS_NAME = "GwybiePrefs";
    private static final String UUID_PREF_NAME = "UUID";
    private final DatabaseHelper databaseHelper;
    private final Context context;
    private SQLiteDatabase database;

    public UUIDDataSource(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.context = context;
    }

    private void openWriteable() {
        this.database = this.databaseHelper.getWritableDatabase();
    }

    private void openReadable() {
        this.database = this.databaseHelper.getReadableDatabase();
    }

    private void close() {
        this.databaseHelper.close();
    }

    public void checkUUID() {
        openReadable();
        String uuid = getUUID();
        if (uuid == null || uuid.isEmpty()) {
            do {
                uuid = generateUUID();
            } while (UUIDExists(uuid));
            close();
            openWriteable();
            insertUUID(uuid);
            saveUUID(uuid);
            close();
            Log.d(getClass().getName(), "uuid saved " + uuid);
        } else {
            Log.d(getClass().getName(), "uuid checked " + uuid);
        }
    }

    public void insertUUID(String uuid) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UUIDEntry.COLUMN_UUID, uuid);
        this.database.insert(DatabaseContract.UUIDEntry.TABLE_NAME, null, values);
    }

    public boolean UUIDExists(String uuid) {
        String query = "SELECT uuid FROM " + DatabaseContract.UUIDEntry.TABLE_NAME + " WHERE uuid = ?";
        String[] args = {uuid};
        return DatabaseUtils.stringForQuery(this.database, query, args) != null;
    }

    private void saveUUID(String uuid) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(UUID_PREF_NAME, uuid);
        editor.apply();
    }

    private String getUUID() {
        SharedPreferences prefs = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(UUID_PREF_NAME, null);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}