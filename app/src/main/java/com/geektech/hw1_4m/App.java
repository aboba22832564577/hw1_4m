package com.geektech.hw1_4m;

import android.app.Application;

import com.geektech.hw1_4m.utils.Prefs;

public class App extends Application {

    public static Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(getApplicationContext());
    }
}
