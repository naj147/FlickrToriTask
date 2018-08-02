package com.example.datalayer.net;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Singleton
public class RetrofitGen {
    private static  String BASE_URL= "https://api.flickr.com/services/rest/";
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
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(http3Gen.okHttpClient())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }
}
