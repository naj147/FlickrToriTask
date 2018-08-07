package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;

import io.reactivex.Observable;

class PhotosLocalDataStore implements PhotosDataStore {
    private final PhotosCache photosCache;
    public PhotosLocalDataStore(PhotosCache photosCache) {
        this.photosCache=photosCache;
    }
    

    @Override
    public Observable<PhotosEntity> photos(int method, String title, String tags) {
        return photosCache.get();
    }
}
