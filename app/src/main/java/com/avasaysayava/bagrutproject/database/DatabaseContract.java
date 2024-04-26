package com.avasaysayava.bagrutproject.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static class UUIDEntry implements BaseColumns {
        public static final String TABLE_NAME = "uuid_table";
        public static final String COLUMN_UUID = "uuid";
    }
}