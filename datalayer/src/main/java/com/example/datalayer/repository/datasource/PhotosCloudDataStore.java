package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.net.API;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class PhotosCloudDataStore implements  PhotosDataStore {
    private final API apiService;
    private final PhotosCache photosCache;
    //TEST PURPOSES
    private  final String METHOD = "flickr.photos.getRecent";
    private  final String api_key= "b59eaa142fbb03d0ba6c93882fd62e30";
    private  final int page=1;
    private final int njcb = 1;
    private final String format = "json";

    public PhotosCloudDataStore(API apiService, PhotosCache photosCache) {
        this.apiService = apiService;
        this.photosCache= photosCache;
    }

    @Override
    public Observable<PhotosEntity> photos() {
        return apiService.listImages(METHOD,api_key,page,format,njcb).doOnNext(new Consumer<PhotosEntity>() {
            @Override
            public void accept(PhotosEntity photosEntity) throws Exception {
                photosCache.put(photosEntity);
            }
        });
    }
}
