package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;
import com.example.datalayer.net.API;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.datalayer.net.API.api_key;
import static com.example.datalayer.net.API.page;
import static com.example.datalayer.net.API.METHOD1;
import static com.example.datalayer.net.API.format;
import static com.example.datalayer.net.API.njcb;

import static org.mockito.BDDMockito.given;

public class PhotosCloudDataStoreTest {
    private  static final String fakeUserID = "123@42";
    @Mock
    API mockedApiService;
    @Mock
    PhotosCache mockedPhotosCache;

    PhotosCloudDataStore photosCloudDataStore ;


    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        photosCloudDataStore = new PhotosCloudDataStore(mockedApiService, mockedPhotosCache);
    }
    @Test
    public void testGetPhotosFromApi(){
        PhotosEntity fakePhotosEntity = new PhotosEntity();
        io.reactivex.Observable<PhotosEntity> fakeObservable = io.reactivex.Observable.just(fakePhotosEntity);
        given(mockedApiService.listImages(METHOD1,api_key,page,format,njcb)).willReturn(fakeObservable);
    }

    @Test
    public void testGetUserFromApi(){
        UserEntity fakeUserEntity = new UserEntity();
        io.reactivex.Observable<UserEntity> fakeObservable = io.reactivex.Observable.just(fakeUserEntity);
        given(mockedApiService.getUser(METHOD1,api_key, fakeUserID,page,format,njcb)).willReturn(fakeObservable);
    }

}
