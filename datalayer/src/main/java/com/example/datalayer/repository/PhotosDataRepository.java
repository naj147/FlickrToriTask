package com.example.datalayer.repository;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;
import com.example.datalayer.entity.mapper.PhotosMapper;
import com.example.datalayer.entity.mapper.UserMapper;
import com.example.datalayer.repository.datasource.PhotosDataStoreFactory;
import com.example.domainlayer.model.Photos;
import com.example.domainlayer.model.User;
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
private final UserMapper userMapper;
//Todo : injection here eventually

@Inject
    public PhotosDataRepository(PhotosDataStoreFactory photosDataStoreFactory, PhotosMapper photosMapper, UserMapper userMapper) {
        this.photosDataStoreFactory = photosDataStoreFactory;
        this.photosMapper = photosMapper;
        this.userMapper=userMapper;
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

    @Override
    public Observable<User> userDetails(int method, String userId) {
        return photosDataStoreFactory.create().userDetails(method,userId).map(new Function<UserEntity, User>() {
            @Override
            public User apply(UserEntity userEntity) throws Exception {
                return userMapper.transform(userEntity);
            }
        });
    }
}
