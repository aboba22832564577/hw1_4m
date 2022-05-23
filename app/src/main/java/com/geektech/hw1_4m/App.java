package com.geektech.hw1_4m;

import android.app.Application;

import androidx.room.Room;

import com.geektech.hw1_4m.data.AppDataBase;
import com.geektech.hw1_4m.utils.Prefs;

public class App extends Application {

    public static Prefs prefs;
    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(getApplicationContext());
        dataBase = Room.databaseBuilder(getApplicationContext()
                ,AppDataBase.class
                ,"dataBase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
