package com.example.datalayer.net;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes =  NetworkModule.class)
@flickrScope
public class RetrofitServiceModule {

    @Provides
    @flickrScope
    API getFlickrService(Retrofit retrofit){
        return  retrofit.create(API.class);
    }

    @Provides
    @flickrScope
    Retrofit getRetrofitService(OkHttpClient okHttpClient,Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://api.flickr.com/services/rest/")
                .build();
    }
}
