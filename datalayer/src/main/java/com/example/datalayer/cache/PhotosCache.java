package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;

import io.reactivex.Observable;

public interface PhotosCache {

    boolean isExpired();
    boolean isCached();
    Observable<PhotosEntity> get();
    void put(PhotosEntity photosEntity);
}
