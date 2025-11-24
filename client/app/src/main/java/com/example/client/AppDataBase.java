package com.example.client;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase db;
    static AppDataBase getInstance(Context context) {
        if (db == null) {
            synchronized (AppDataBase.class) {
                if (db == null) {
                    db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "dbRoom")
                            .addMigrations()
                            .build();
                }
            }
        }
        return db;
    }


    public abstract StudentDao studentDao();

}
