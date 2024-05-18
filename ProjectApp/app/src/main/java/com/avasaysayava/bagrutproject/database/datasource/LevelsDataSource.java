package com.avasaysayava.bagrutproject.database.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

public class LevelsDataSource extends DataSource {
    private final String PREFS_NAME = this.context.getResources().getString(R.string.app_name);

    public LevelsDataSource(Context context) {
        super(context);
    }

    @SuppressLint("Range")
    public Long getBestTime(int level) {
        Cursor cursor = this.database.query(DatabaseContract.LevelsEntry.TABLE_NAME,
                new String[]{DatabaseContract.LevelsEntry.COLUMN_TIME},
                DatabaseContract.LevelsEntry.COLUMN_LEVEL + " = ?",
                new String[]{String.valueOf(level)},
                null,
                null,
                DatabaseContract.LevelsEntry.COLUMN_TIME + " ASC");
        Long best = null;
        if (cursor.moveToFirst())
            best = cursor.getLong(cursor.getColumnIndex(
                    DatabaseContract.LevelsEntry.COLUMN_TIME));
        cursor.close();
        return best;
    }

    @SuppressLint("Range")
    public List<Long> getAllTimes(int level) {
        Cursor cursor = this.database.query(DatabaseContract.LevelsEntry.TABLE_NAME,
                new String[]{DatabaseContract.LevelsEntry.COLUMN_TIME},
                DatabaseContract.LevelsEntry.COLUMN_LEVEL + "=?",
                new String[]{String.valueOf(level)},
                null,
                null,
                DatabaseContract.LevelsEntry.COLUMN_TIME + " ASC");
        List<Long> times = new ArrayList<>();
        while (cursor.moveToNext())
            times.add(cursor.getLong(cursor.getColumnIndex(
                    DatabaseContract.LevelsEntry.COLUMN_TIME)));
        cursor.close();
        return times;
    }

    public String getLastTime(int level) {
        return this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE)
                .getString(DatabaseContract.LevelsEntry.COLUMN_LEVEL + level, null);
    }

    public void setLastTime(String time, int level) {
        this.context.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(DatabaseContract.LevelsEntry.COLUMN_LEVEL + level, time)
                .apply();
    }

    public void addTime(long time, int level) {
        this.database.execSQL("INSERT INTO " + DatabaseContract.LevelsEntry.TABLE_NAME
                + "(" + DatabaseContract.LevelsEntry.COLUMN_LEVEL + ","
                + DatabaseContract.LevelsEntry.COLUMN_TIME + ") VALUES ("
                + level + "," + time + ")");
    }
}
