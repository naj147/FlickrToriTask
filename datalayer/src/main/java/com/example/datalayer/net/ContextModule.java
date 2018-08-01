package com.example.datalayer.net;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;
    public ContextModule(Context context){
        this.context = context;
    }
    @Provides
    @Named("Application_Context")
    public Context context(){
        return context;
    }
}
