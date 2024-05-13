package com.avasaysayava.bagrutproject.database.datasource;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.avasaysayava.bagrutproject.database.DatabaseContract;

public class LevelDataSource extends DataSource {
    private String LEVEL_NAME;

    public LevelDataSource(Context context, String levelName) {
        super(context);
        this.LEVEL_NAME = levelName;
    }

    public String getTimeByUUID(String uuid) {
        String selection = DatabaseContract.UserEntry.COLUMN_UUID + "=?";
        String[] args = {uuid};
        String[] columns = {this.LEVEL_NAME};
        Cursor cursor = this.database.query(DatabaseContract.UserEntry.TABLE_NAME, columns, selection, args, null, null, null);
        if (!cursor.moveToFirst()) return null;
        @SuppressLint("Range") String out = cursor.getString(cursor.getColumnIndex(this.LEVEL_NAME));
        cursor.close();
        return out;
    }

    public void setLevel(String levelName) {
        this.LEVEL_NAME = levelName;
    }

    public int getRankByUUID(String uuid) {
        int rank = -1;
        Cursor cursor = this.database.rawQuery("SELECT " + DatabaseContract.UserEntry.COLUMN_UUID + " FROM " + DatabaseContract.UserEntry.TABLE_NAME + " WHERE " + this.LEVEL_NAME + " IS NOT NULL ORDER BY " + this.LEVEL_NAME + ", " + DatabaseContract.UserEntry.COLUMN_UUID + " ASC", null);

        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_UUID);
            for (int i = 1; cursor.moveToNext(); i++) {
                if (cursor.getString(columnIndex).equals(uuid)) {
                    rank = i;
                    break;
                }
            }
            cursor.close();
        }

        return rank;
    }

    public void updateTimeByUUID(String time, String uuid) {
        ContentValues values = new ContentValues();
        values.put(this.LEVEL_NAME, time);
        this.database.update(DatabaseContract.UserEntry.TABLE_NAME, values, DatabaseContract.UserEntry.COLUMN_UUID + "='" + uuid + "'", null);
    }

    @SuppressLint("Range")
    public String getTimeByRank(int rank) {
        if (rank == -1) return null;

        String out = null;
        Cursor cursor = this.database.rawQuery("SELECT " + this.LEVEL_NAME + " FROM " + DatabaseContract.UserEntry.TABLE_NAME + " WHERE " + this.LEVEL_NAME + " IS NOT NULL ORDER BY " + this.LEVEL_NAME + ", " + DatabaseContract.UserEntry.COLUMN_UUID + " ASC", null);

        if (cursor != null) {
            if (cursor.moveToPosition(rank - 1)) {
                out = cursor.getString(cursor.getColumnIndex(this.LEVEL_NAME));
            }
            cursor.close();
        }

        return out;
    }
}
