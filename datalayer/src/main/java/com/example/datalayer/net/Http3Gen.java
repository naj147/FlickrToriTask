package com.example.datalayer.net;

import com.example.datalayer.cache.Http3Cache;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;


/**
 * A Class to generate an instance of OkHttp3
 */
@Singleton
public class Http3Gen {
    private  final  Http3Cache http3Cache;

    @Inject
    public Http3Gen(Http3Cache http3Cache) {
        this.http3Cache = http3Cache;
    }

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

    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .cache(this.http3Cache.getCache())
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .build();
    }
}
