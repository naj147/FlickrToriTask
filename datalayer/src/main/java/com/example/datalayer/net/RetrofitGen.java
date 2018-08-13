package com.example.datalayer.net;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A Class to generate an instance of Retrofit to be used with Flickr Api
 */
@Singleton
public class RetrofitGen {
    private static  String BASE_URL= "https://api.flickr.com/";
    private final Http3Gen http3Gen;
    private static Retrofit retrofit;
@Inject
public RetrofitGen(Http3Gen http3Gen) {
    this.http3Gen = http3Gen;
}

    public  Retrofit getRetrofitService() {
        if(retrofit!=null){
            return retrofit;
        }
        retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(new itemTypeAdapterFactory()).setExclusionStrategies(new AnnotationExclusionStrategy()).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(http3Gen.okHttpClient())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }
}
