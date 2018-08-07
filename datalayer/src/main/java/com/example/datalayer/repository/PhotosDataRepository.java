package com.example.datalayer.repository;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.mapper.PhotosMapper;
import com.example.datalayer.repository.datasource.PhotosDataStoreFactory;
import com.example.domainlayer.model.Photos;
import com.example.domainlayer.repository.PhotosRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import timber.log.Timber;

@Singleton
public class PhotosDataRepository implements PhotosRepository {
private final PhotosDataStoreFactory photosDataStoreFactory;
private  final PhotosMapper photosMapper;
//Todo : injection here eventually

@Inject
    public PhotosDataRepository(PhotosDataStoreFactory photosDataStoreFactory, PhotosMapper photosMapper) {
        this.photosDataStoreFactory = photosDataStoreFactory;
        this.photosMapper = photosMapper;
    }

    @Override
    public Observable<Photos> photos(int method,String title, String tags) {
        return photosDataStoreFactory.create().photos( method,title,tags).map(new Function<PhotosEntity, Photos>() {
            @Override
            public Photos apply(PhotosEntity photosEntity) throws Exception {
//                Timber.tag("HEY").d("I was in PhotosDataRepo");
//                Timber.tag("HEY").d("Fatzo "+ photosEntity.toString());
                return photosMapper.transform(photosEntity);
            }
        });
    }
}
