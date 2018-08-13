package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;
import com.example.datalayer.net.API;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.example.datalayer.net.API.METHOD1;
import static com.example.datalayer.net.API.METHOD2;
import static com.example.datalayer.net.API.METHOD3;
import static com.example.datalayer.net.API.api_key;
import static com.example.datalayer.net.API.format;
import static com.example.datalayer.net.API.njcb;
import static com.example.datalayer.net.API.page;

/**
 * {@link PhotosDataStore} implementation based on connections to the api (Cloud).
 */
public class PhotosCloudDataStore implements  PhotosDataStore {
    private final API apiService;
    private final PhotosCache photosCache;

    /**
     * Construct a {@link PhotosDataStore} based on connections to the api (Cloud).
     *
     * @param apiService  The {@link API} implementation to use.
     * @param photosCache A {@link PhotosCache} to cache data retrieved from the api.
     */
    public PhotosCloudDataStore(API apiService, PhotosCache photosCache) {
        this.apiService = apiService;
        this.photosCache= photosCache;
    }

    @Override
    public Observable<PhotosEntity> photos(int method, String param1, int param2) {
        switch (method){
            case 2 :
                return apiService.searchForImages(METHOD2, api_key, param1, param1, param2, format, njcb).doOnNext(new Consumer<PhotosEntity>() {
                    @Override
                    public void accept(PhotosEntity photosEntity) {
                        photosCache.put(photosEntity);
                    }
                });

            default : return apiService.listImages(METHOD1,api_key,param2,format,njcb).doOnNext(new Consumer<PhotosEntity>() {
                @Override
                public void accept(PhotosEntity photosEntity) {
                    photosCache.put(photosEntity);
                }
            });

        }


    }

    @Override
    public Observable<UserEntity> userDetails(int method, String param1) {
        return apiService.getUser(METHOD3,api_key,param1,page,format,njcb).doOnNext(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) {
                photosCache.put(userEntity);
            }
        });
    }
}

