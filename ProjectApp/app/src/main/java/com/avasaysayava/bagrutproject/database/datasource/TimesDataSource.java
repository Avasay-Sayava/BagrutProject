package com.avasaysayava.bagrutproject.database.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

public class TimesDataSource extends DataSource {
    private final String PREFS_NAME = this.context.getResources().getString(R.string.app_name);

    public TimesDataSource(Context context) {
        super(context);
    }

    @SuppressLint("Range")
    public Long getBestTime(int level) {
        Cursor cursor = this.database.query(DatabaseContract.TimesEntry.TABLE_NAME,
                new String[]{DatabaseContract.TimesEntry.COLUMN_TIME},
                DatabaseContract.TimesEntry.COLUMN_LEVEL + " = ?",
                new String[]{String.valueOf(level)},
                null,
                null,
                DatabaseContract.TimesEntry.COLUMN_TIME + " ASC");
        Long best = null;
        if (cursor.moveToFirst())
            best = cursor.getLong(cursor.getColumnIndex(
                    DatabaseContract.TimesEntry.COLUMN_TIME));
        cursor.close();
        return best;
    }

    @SuppressLint("Range")
    public List<Long> getAllTimes(int level) {
        Cursor cursor = this.database.query(DatabaseContract.TimesEntry.TABLE_NAME,
                new String[]{DatabaseContract.TimesEntry.COLUMN_TIME},
                DatabaseContract.TimesEntry.COLUMN_LEVEL + "=?",
                new String[]{String.valueOf(level)},
                null,
                null,
                DatabaseContract.TimesEntry.COLUMN_TIME + " ASC");
        List<Long> times = new ArrayList<>();
        while (cursor.moveToNext())
            times.add(cursor.getLong(cursor.getColumnIndex(
                    DatabaseContract.TimesEntry.COLUMN_TIME)));
        cursor.close();
        return times;
    }

    public Long getLastTime(int level) {
        long out = this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE)
                .getLong(DatabaseContract.TimesEntry.COLUMN_LEVEL + level, -1);
        return out == -1 ? null : out;
    }

    public void setLastTime(long time, int level) {
        this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putLong(DatabaseContract.TimesEntry.COLUMN_LEVEL + level, time)
                .apply();
    }

    public void addTime(long time, int level) {
        this.database.execSQL("INSERT INTO " + DatabaseContract.TimesEntry.TABLE_NAME
                + "(" + DatabaseContract.TimesEntry.COLUMN_LEVEL + ","
                + DatabaseContract.TimesEntry.COLUMN_TIME + ") VALUES ("
                + level + "," + time + ")");
    }
}
