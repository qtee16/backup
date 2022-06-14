package com.example.sqliteimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void insertData(String name, String desc, byte[] img) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Object VALUES (null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, desc);
        statement.bindBlob(3, img);

        statement.executeInsert();
    }

    public void updateData(int ID, String name, String desc, byte[] img) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Object " +
                "SET Name = ?, Desc = ?, Image = ?" +
                "WHERE Id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.bindString(1, name);
        statement.bindString(2, desc);
        statement.bindBlob(3, img);
        statement.bindLong(4, ID);

        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
