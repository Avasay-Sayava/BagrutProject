package com.avasaysayava.bagrutproject.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static class TimesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Times";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LEVEL = "level";
    }
}