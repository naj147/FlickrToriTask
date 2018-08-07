package com.example.naj_t.flickrtoritask;

import android.app.Application;


import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.components.DaggerApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class AndroidApplication extends Application{
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeDevEnv();
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    void initializeDevEnv(){
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            LeakCanary.install(this);
        }

    }
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
