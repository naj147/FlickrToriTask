package com.example.datalayer.repository;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;
import com.example.datalayer.entity.mapper.PhotosMapper;
import com.example.datalayer.entity.mapper.UserMapper;
import com.example.datalayer.repository.datasource.PhotosCloudDataStore;
import com.example.datalayer.repository.datasource.PhotosDataStore;
import com.example.datalayer.repository.datasource.PhotosDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PhotosDataRepositoryTest {
    @Mock
    private PhotosDataStoreFactory photosDataStoreFactory;
    @Mock
    private PhotosMapper photosMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PhotosDataStore photosDataStore;

    private PhotosDataRepository photosDataRepository;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photosDataRepository = new PhotosDataRepository(photosDataStoreFactory,photosMapper,userMapper);
        given(photosDataStoreFactory.create()).willReturn(photosDataStore);

    }
    @Test
    public void getPhotosTest(){
        PhotosEntity photosEntity = new PhotosEntity();
        UserEntity userEntity = new UserEntity();
        given(photosDataStore.photos(0,null,0)).willReturn(Observable.just(photosEntity));
        given(photosDataStore.userDetails(0,null)).willReturn(Observable.just(userEntity));
        photosDataRepository.photos(0,null,0);
        photosDataRepository.userDetails(0,null);
        verify(photosDataStoreFactory,times(2)).create();
        verify(photosDataStore).photos(0,null,0);
        verify(photosDataStore).userDetails(0,null);
    }


}
