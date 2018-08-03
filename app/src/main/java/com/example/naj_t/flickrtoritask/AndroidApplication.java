package com.example.naj_t.flickrtoritask;

import android.app.Activity;
import android.app.Application;


import com.example.naj_t.flickrtoritask.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.components.DaggerApplicationComponent;
import com.example.naj_t.flickrtoritask.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class AndroidApplication extends Application{
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
