package com.example.datalayer.net;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlickrServiceGenerator {
    RetrofitGen retrofitGen;
@Inject
    public FlickrServiceGenerator(RetrofitGen retrofitGen) {
        this.retrofitGen = retrofitGen;
    }
    private static API api;

    public API getAPI(){
        if(api==null)
            api=retrofitGen.getRetrofitService().create(API.class);
        return api;
    }
}
