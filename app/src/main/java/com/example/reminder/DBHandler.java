package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "reminder.db";

    public static final String TABLE_REMINDER = "reminder";
    public static final String COLUMN_REMINDER_ID = "_id";
    public static final String COLUMN_REMINDER_TITLE = "title";
    public static final String COLUMN_REMINDER_DATE = "date";
    public static final String COLUMN_REMINDER_TYPE = "type";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory cursorFactory){
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_REMINDER + "(" +
                COLUMN_REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REMINDER_TITLE + " TEXT, " +
                COLUMN_REMINDER_DATE + " TEXT, " +
                COLUMN_REMINDER_TYPE + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        onCreate(sqLiteDatabase);
    }

    public void addShoppingList (String title, String date, String type){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_REMINDER_TITLE, title);
        values.put(COLUMN_REMINDER_DATE, date);
        values.put(COLUMN_REMINDER_TYPE, type);

        db.insert(TABLE_REMINDER, null, values);
        db.close();
    }

    public Cursor getReminders(){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_REMINDER, null);
    }
}
