package com.avasaysayava.bagrutproject.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_UUID = "uuid";
    }
}