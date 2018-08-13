package com.example.datalayer.cache;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;

/**
 * {@link Http3Cache} Class implementation for initializing the cache file and acquiring it to be used with OkHttp3
 */
@Singleton
public class Http3CacheImpl implements Http3Cache{

    private final Context context;
    private final File cacheDir;
@Inject
public Http3CacheImpl(Context context) {
    this.context = context;
    this.cacheDir = cacheFile(this.context);
}

    private File cacheFile(Context context) {
        return new File(context.getCacheDir(),"okhttp_cache");
    }

    private Cache cache(File cacheFile) {
        return new Cache(cacheFile, 8*1000*1000); //8mb
    }

    @Override
    public Cache getCache() {
        return cache(this.cacheDir);
    }
}
