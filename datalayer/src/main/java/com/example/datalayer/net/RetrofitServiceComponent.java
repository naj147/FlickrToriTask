package com.example.datalayer.net;

import dagger.Component;
import retrofit2.Retrofit;
@flickrScope
@Component(modules = RetrofitServiceModule.class )
public interface RetrofitServiceComponent {

Retrofit getRetrofitService();

API getFlickrService();
}
