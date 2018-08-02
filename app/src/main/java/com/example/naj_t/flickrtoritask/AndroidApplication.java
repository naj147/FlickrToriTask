package com.example.naj_t.flickrtoritask;

import android.app.Activity;
import android.app.Application;


import timber.log.Timber;

public class AndroidApplication extends Application{
   public AndroidApplication get(Activity activity){
        return  (AndroidApplication) activity.getApplication();
   }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
