package com.example.userapp;

import android.app.Application;

import androidx.room.Room;

import com.example.userapp.database.AppDatabase;

public class MyApplication extends Application {
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user-database").build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
