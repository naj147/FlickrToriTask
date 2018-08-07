package com.example.datalayer.repository.datasource;

import com.example.datalayer.entity.PhotosEntity;

import io.reactivex.Observable;

public interface PhotosDataStore {
    Observable<PhotosEntity> photos(int method,String title, String tags);
}
