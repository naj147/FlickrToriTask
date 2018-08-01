package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.net.API;

public class PhotosDataStoreFactory {
    private final PhotosCache photosCache;
    private final API api;

    public PhotosDataStoreFactory(PhotosCache photosCache, API api) {
        this.photosCache = photosCache;
        this.api = api;
    }


    public PhotosDataStore create(){
        if(!photosCache.isExpired() && photosCache.isCached()){
            return new PhotosLocalDataStore(photosCache);
        }else{
            return new PhotosCloudDataStore(api,photosCache);
        }
    }
}
