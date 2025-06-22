package com.welipitiya.trackerhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "favorites_db";  // Database name
    private static final int DB_VERSION = 1;               // Database version
    private static final String TABLE_NAME = "favorite_bikes";  // Table name

    // Column names for the favorite_bikes table
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_DESC = "description";
    private static final String COL_IMAGE = "image";

    // Constructor
    public FavoriteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Called when DB is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table SQL with columns and constraints
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  // ID auto increments
                COL_NAME + " TEXT UNIQUE, " +                      // Name must be unique
                COL_DESC + " TEXT, " +
                COL_IMAGE + " INTEGER)";
        db.execSQL(query);
    }

    // Called when DB version is upgraded (optional here)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Add a bike to favorites, prevent duplicates
    public void addFavorite(Bike bike) {
        if (isFavorite(bike.getName())) return;  // Check if already favorite, skip if yes

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, bike.getName());
        values.put(COL_DESC, bike.getDescription());
        values.put(COL_IMAGE, bike.getImageResource());

        db.insert(TABLE_NAME, null, values);  // Insert bike into DB
        db.close();
    }

    // Remove a bike from favorites by its name
    public void removeFavorite(String bikeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_NAME + "=?", new String[]{bikeName});  // Delete matching row
        db.close();
    }

    // Check if a bike is already marked as favorite
    public boolean isFavorite(String bikeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_NAME + "=?", new String[]{bikeName}, null, null, null);
        boolean exists = cursor.getCount() > 0;  // Exists if count > 0
        cursor.close();
        db.close();
        return exists;
    }

    // Get a list of all favorite bikes from the database
    public List<Bike> getAllFavorites() {
        List<Bike> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query all rows ordered by bike name ascending
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                // Read each column for the bike
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESC));
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IMAGE));

                // Create Bike object with favorite=true
                Bike bike = new Bike(name, desc, image, true);
                list.add(bike);  // Add to list
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}
