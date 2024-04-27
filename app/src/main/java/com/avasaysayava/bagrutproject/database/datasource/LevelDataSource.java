package com.avasaysayava.bagrutproject.database.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.avasaysayava.bagrutproject.database.DatabaseContract;

public class LevelDataSource extends DataSource {
    private String COLUMN_NAME;

    public LevelDataSource(Context context, String columnName) {
        super(context);
        this.COLUMN_NAME = columnName;
    }

    public String getTimeByUUID(String uuid) {
        String selection = DatabaseContract.UserEntry.COLUMN_UUID + "=?";
        String[] args = {uuid};
        String[] columns = {this.COLUMN_NAME};
        Cursor cursor = this.database.query(DatabaseContract.UserEntry.TABLE_NAME, columns, selection, args, null, null, null);
        if (!cursor.moveToFirst())
            return null;
        @SuppressLint("Range") String out = cursor.getString(cursor.getColumnIndex(this.COLUMN_NAME));
        cursor.close();
        return out;
    }

    public void setColumn(String columnName) {
        this.COLUMN_NAME = columnName;
    }

    public int getRankByTime(String time) {
        int rank = -1;
        if (time == null)
            return -1;
        Cursor cursor = this.database.rawQuery("SELECT " + this.COLUMN_NAME + " FROM " +
                DatabaseContract.UserEntry.TABLE_NAME + " WHERE " + this.COLUMN_NAME + " IS NOT NULL ORDER BY " +
                this.COLUMN_NAME + ", " + DatabaseContract.UserEntry.COLUMN_UUID + " ASC", null
        );

        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(this.COLUMN_NAME);
            for (int i = 1; cursor.moveToNext(); i++) {
                if (cursor.getString(columnIndex).equals(time)) {
                    rank = i;
                    break;
                }
            }
            cursor.close();
        }

        return rank;
    }

    @SuppressLint("Range")
    public String getTimeByRank(int rank) {
        if (rank == -1)
            return null;

        String out = null;
        Cursor cursor = this.database.rawQuery("SELECT " + this.COLUMN_NAME + " FROM " +
                DatabaseContract.UserEntry.TABLE_NAME + " WHERE " + this.COLUMN_NAME + " IS NOT NULL ORDER BY " +
                this.COLUMN_NAME + ", " + DatabaseContract.UserEntry.COLUMN_UUID + " ASC", null
        );

        if (cursor != null) {
            if (cursor.moveToPosition(rank - 1)) {
                out = cursor.getString(cursor.getColumnIndex(this.COLUMN_NAME));
            }
            cursor.close();
        }

        return out;
    }
}
