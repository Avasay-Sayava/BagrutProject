package com.avasaysayava.bagrutproject.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static class LevelsEntry implements BaseColumns {
        public static final String TABLE_NAME = "Levels";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LEVEL = "level";
    }
}