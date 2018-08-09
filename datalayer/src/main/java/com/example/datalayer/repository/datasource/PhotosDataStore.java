package com.example.datalayer.repository.datasource;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import io.reactivex.Observable;

public interface PhotosDataStore {
    Observable<PhotosEntity> photos(int method,String param1, int param2);

    Observable<UserEntity> userDetails(int method, String param1);

}
