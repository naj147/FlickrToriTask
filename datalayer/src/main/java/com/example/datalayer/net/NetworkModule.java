package com.example.datalayer.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class NetworkModule {
    @Provides
    @flickrScope
    public Gson gson(){
      return new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
    }

    @Provides
    @flickrScope
    public  HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor ;

        interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return  interceptor;
    }
    @Provides
    @flickrScope
    File cacheFile(@Named("Application_Context") Context context){
        return new File(context.getCacheDir(),"okhttp_cache");
    }

    @Provides
    @flickrScope
    Cache cache (File cacheFile){
        return new Cache(cacheFile, 8*1000*1000); //8mb
    }


    @Provides
    @flickrScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor){
        return  new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }
}
