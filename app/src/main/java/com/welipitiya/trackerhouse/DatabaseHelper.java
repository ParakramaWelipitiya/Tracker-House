package com.welipitiya.trackerhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TrackerHouse.db";   // Database name
    public static final int DATABASE_VERSION = 2;                   // Database version (for upgrades)

    public static final String TABLE_USERS = "users";               // Table name for users
    public static final String COLUMN_ID = "id";                     // Primary key column
    public static final String COLUMN_EMAIL = "email";               // User email (unique)
    public static final String COLUMN_USERNAME = "username";         // Username column
    public static final String COLUMN_PASSWORD = "password";         // Password column

    // Constructor to create/open the database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called once when database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create users table with columns and constraints
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Auto increment ID
                COLUMN_EMAIL + " TEXT UNIQUE, " +                      // Email must be unique
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);  // Execute SQL to create table
    }

    // Called when database version changes - handle upgrade logic here
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Insert a new user into users table
    public boolean insertUser(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();  // Open database for writing
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);         // Add email value
        values.put(COLUMN_USERNAME, username);   // Add username value
        values.put(COLUMN_PASSWORD, password);   // Add password value
        long result = db.insert(TABLE_USERS, null, values);  // Insert row
        return result != -1;  // Return true if insert successful, false if failed
    }

    // Check if a user already exists with the given email
    public boolean checkUserExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();  // Open database for reading
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;  // True if at least one row found
        cursor.close();                          // Close cursor to free resources
        return exists;
    }

    // Validate user login by checking email and password
    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();  // Open database for reading
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});
        boolean valid = cursor.getCount() > 0;  // True if matching user found
        cursor.close();
        return valid;
    }

    // Get username for a given email (for display or profile)
    public String getUsernameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String username = cursor.getString(0);  // Get username from first column
            cursor.close();
            return username;
        } else {
            cursor.close();
            return null;  // No username found for this email
        }
    }
}
