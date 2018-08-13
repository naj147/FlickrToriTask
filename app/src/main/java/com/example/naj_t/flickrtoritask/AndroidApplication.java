package com.example.naj_t.flickrtoritask;

import android.app.Application;
import android.content.Context;

import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.components.DaggerApplicationComponent;
import com.example.naj_t.flickrtoritask.DPINJ.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import timber.log.Timber;


/**
 * Android Main Application
 * Used to instantiate Realm DB instance, Timber Instant and LeakCanary Instant
 */
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
        initializingRealm(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            LeakCanary.install(this);
        }

    }

    public void initializingRealm(Context context) {
        Realm.init(context);
        //THIS CREATES A MEMORY LEAK AND WAS USED ONLY FOR DEV PURPOSES

        //if(BuildConfig.DEBUG){
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(context)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
//                        .build());
        //}
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
