package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Interface for Realm.io
 * It's used to cache the different entities received by OkHttp3
 * *
 */

public interface PhotosCache {

    boolean isExpired();

    boolean isCached(UserEntity userEntity);

    boolean isCached();

    Observable<PhotosEntity> get();

    Observable<UserEntity> getUser(String userID);

    void put(UserEntity userEntity);

    void put(PhotosEntity photosEntity);
}
