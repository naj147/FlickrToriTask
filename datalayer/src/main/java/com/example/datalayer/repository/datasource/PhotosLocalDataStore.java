package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import io.reactivex.Observable;

/**
 * {@link PhotosDataStore} implementation based on file system data store.
 */
class PhotosLocalDataStore implements PhotosDataStore {
    private final PhotosCache photosCache;

    /**
     * Construct a {@link PhotosLocalDataStore} based file system data store.
     *
     * @param photosCache A {@link PhotosCache} to cache data retrieved from the api.
     */
    public PhotosLocalDataStore(PhotosCache photosCache) {
        this.photosCache=photosCache;
    }


    @Override
    public Observable<PhotosEntity> photos(int method, String title, int page) {
        return photosCache.get();
    }

    @Override
    public Observable<UserEntity> userDetails(int method, String param1) {
        return photosCache.getUser(param1);
    }
}
