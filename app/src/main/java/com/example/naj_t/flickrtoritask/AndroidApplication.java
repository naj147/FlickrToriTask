package com.example.naj_t.flickrtoritask;

import android.app.Application;


import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.components.DaggerApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.modules.ApplicationModule;

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
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
