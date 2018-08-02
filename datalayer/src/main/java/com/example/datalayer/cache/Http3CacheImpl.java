package com.example.datalayer.cache;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;

@Singleton
public class Http3CacheImpl implements Http3Cache{

    private final Context context;
    private final File cacheDir;
@Inject
    public Http3CacheImpl(Context context) {
        this.context = context;
        this.cacheDir = cacheFile(this.context);
    }
    File cacheFile(Context context){
        return new File(context.getCacheDir(),"okhttp_cache");
    }
    Cache cache (File cacheFile){
        return new Cache(cacheFile, 8*1000*1000); //8mb
    }

    @Override
    public Cache getCache() {
        return cache(this.cacheDir);
    }
}
