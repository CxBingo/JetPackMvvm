package com.bingo.basemodule;

import android.content.Context;

import androidx.multidex.MultiDexApplication;


/**
 * Created by francisbingo on 3/12/21 11:28 AM
 *
 */
public class BaseApplication extends MultiDexApplication {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }
}
