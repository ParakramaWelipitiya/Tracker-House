package com.welipitiya.trackerhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BikeDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COL_NAME = "name";
    private static final String COL_IMAGE = "image";
    private static final String COL_DESC = "description";
    private static final String COL_PRICE = "price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAV_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + COL_NAME + " TEXT PRIMARY KEY,"
                + COL_IMAGE + " INTEGER,"
                + COL_DESC + " TEXT,"
                + COL_PRICE + " TEXT"
                + ")";
        db.execSQL(CREATE_FAV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    // Add bike to favorites
    public void addFavorite(String name, int image, String desc, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_IMAGE, image);
        values.put(COL_DESC, desc);
        values.put(COL_PRICE, price);
        db.insertWithOnConflict(TABLE_FAVORITES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    // Remove bike from favorites
    public void removeFavorite(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, COL_NAME + "=?", new String[]{name});
        db.close();
    }

    // Check if bike is in favorites
    public boolean isFavorite(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, null, COL_NAME + "=?", new String[]{name}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Get all favorite bikes
    public List<BikeModel> getAllFavorites() {
        List<BikeModel> favList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, null, null, null, null, null, COL_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IMAGE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESC));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRICE));
                favList.add(new BikeModel(name, image, desc, price, true));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return favList;
    }

}
