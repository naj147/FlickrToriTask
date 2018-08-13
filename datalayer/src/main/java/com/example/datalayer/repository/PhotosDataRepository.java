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

/**
 * {@link PhotosRepository} for retrieving user and photos data.
 */
@Singleton
public class PhotosDataRepository implements PhotosRepository {
private final PhotosDataStoreFactory photosDataStoreFactory;
private  final PhotosMapper photosMapper;
private final UserMapper userMapper;

    /**
     * Constructs a {@link PhotosRepository}.
     *
     * @param photosDataStoreFactory factory to construct different data source implementations.
     * @param photosMapper           {@link PhotosMapper}.
     * @param userMapper             {@link UserMapper}.
     */
@Inject
public PhotosDataRepository(PhotosDataStoreFactory photosDataStoreFactory, PhotosMapper photosMapper, UserMapper userMapper) {
    this.photosDataStoreFactory = photosDataStoreFactory;
    this.photosMapper = photosMapper;
    this.userMapper = userMapper;
}

    @Override
    public Observable<Photos> photos(int method, String title, int page) {
        return photosDataStoreFactory.create().photos( method,title,page).map(new Function<PhotosEntity, Photos>() {
            @Override
            public Photos apply(PhotosEntity photosEntity) {
                return photosMapper.transform(photosEntity);
            }
        });
    }

    @Override
    public Observable<User> userDetails(int method, String userId) {
        return photosDataStoreFactory.create().userDetails(method,userId).map(new Function<UserEntity, User>() {
            @Override
            public User apply(UserEntity userEntity) {
                return userMapper.transform(userEntity);
            }
        });
    }
}
