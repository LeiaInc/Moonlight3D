package com.limelight;

import android.app.Application;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeiaHelper.init(this);
    }
}
