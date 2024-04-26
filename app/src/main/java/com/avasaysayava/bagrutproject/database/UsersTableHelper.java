package com.avasaysayava.bagrutproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersTableHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database";
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN1 = "id";
    public static final String COLUMN2 = "username";
    public static final String COLUMN3 = "password";

    public UsersTableHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN2 + " TEXT NOT NULL, " + COLUMN3 + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("Upgrading is not supported.");
    }

    public boolean addData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN2, username);
        values.put(COLUMN3, password);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public String getPasswordByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.stringForQuery(db, "SELECT password FROM " + TABLE_NAME + " WHERE username='" + username + "'", null);
    }

    public boolean doesExist(String username, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN2 + " = ? AND " + COLUMN3 + " = ?";
        String[] args = {username, password};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public int getId(String username, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN2 + " = ? AND " + COLUMN3 + " = ?";
        String[] args = {username, password};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);

        cursor.moveToFirst();
        int out = cursor.getInt(0);
        cursor.close();

        return out;
    }
}
