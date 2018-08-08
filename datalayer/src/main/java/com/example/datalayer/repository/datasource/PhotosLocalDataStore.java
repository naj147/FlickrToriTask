package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

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

    @Override
    public Observable<UserEntity> userDetails(int method, String param1) {
        return photosCache.getUser(param1);
    }
}
