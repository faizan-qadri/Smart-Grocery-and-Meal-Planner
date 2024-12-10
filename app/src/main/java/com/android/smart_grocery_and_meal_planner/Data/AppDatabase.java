package com.android.smart_grocery_and_meal_planner.Data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserPreferences.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserPreferencesDao userPreferencesDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database").build();
        }
        return INSTANCE;
    }
}