package com.avasaysayava.bagrutproject.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.DatabaseContract;

import java.util.UUID;

public class UUIDDataSource extends DataSource {
    private final String PREFS_NAME = this.context.getResources().getString(R.string.app_name);
    private final String UUID_PREF_NAME = "UUID";

    public UUIDDataSource(Context context) {
        super(context);
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
        SharedPreferences.Editor editor = this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(this.UUID_PREF_NAME, uuid);
        editor.apply();
    }

    public String getUUID() {
        SharedPreferences prefs = this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(this.UUID_PREF_NAME, null);
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}