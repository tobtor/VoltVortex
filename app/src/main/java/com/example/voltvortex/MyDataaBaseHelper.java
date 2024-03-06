package com.example.voltvortex;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MyDataaBaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "VoltVortex.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "projekt";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final boolean COLUMN_IS_MANY_CITIES = false;
    private static final boolean COLUMN_IS_MANY_CONTACT_PERSONS = false;

    public MyDataaBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCEMENTATION, " +
                COLUMN_PROJECT_NAME + " TEXT, " +
                COLUMN_IS_MANY_CITIES + " BOOLEAN, " +
                COLUMN_IS_MANY_CONTACT_PERSONS + " BOOLEAN);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
