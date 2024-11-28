package com.example.baitap7sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String subject, String description) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SUBJECT, subject);
        values.put(DatabaseHelper.COLUMN_DESC, description);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public Cursor fetch() {
        String[] columns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_SUBJECT, DatabaseHelper.COLUMN_DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public int update(long id, String subject, String description) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SUBJECT, subject);
        values.put(DatabaseHelper.COLUMN_DESC, description);
        return database.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_ID + " = " + id, null);
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID + "=" + id, null);
    }
}