package ua.sanya5791.mysorttest;

import android.app.Application;

import ua.sanya5791.mysorttest.model.Singleton;

/**
 * Created by sanya on 25.05.2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initSinletons();
    }

    private void initSinletons() {
        Singleton.initInstance();
    }

}
