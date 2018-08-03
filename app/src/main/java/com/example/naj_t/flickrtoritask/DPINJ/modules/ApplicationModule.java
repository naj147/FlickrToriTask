package com.example.naj_t.flickrtoritask.DPINJ.modules;

import android.content.Context;

import com.example.datalayer.cache.Http3Cache;
import com.example.datalayer.cache.Http3CacheImpl;
import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.cache.PhotosCacheImpl;
import com.example.datalayer.executor.JobExecutor;
import com.example.datalayer.repository.PhotosDataRepository;
import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.repository.PhotosRepository;
import com.example.naj_t.flickrtoritask.AndroidApplication;
import com.example.naj_t.flickrtoritask.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    Http3Cache provideHttp3Cache(Http3CacheImpl http3Cache) {
        return http3Cache;
    }
    @Provides @Singleton
    Http3CacheImpl provideHttp3CacheImpl(Context context){
        return  new Http3CacheImpl(context);
    }

    @Provides @Singleton
    PhotosRepository providePhotosDataRepository(PhotosDataRepository photosDataRepository) {
        return photosDataRepository;
    }
    @Provides @Singleton
    PhotosCache photosCache(PhotosCacheImpl photosCache){
        return photosCache;
    }
}
