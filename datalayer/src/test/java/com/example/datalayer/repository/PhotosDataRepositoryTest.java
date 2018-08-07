package com.example.datalayer.repository;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.mapper.PhotosMapper;
import com.example.datalayer.repository.datasource.PhotosCloudDataStore;
import com.example.datalayer.repository.datasource.PhotosDataStore;
import com.example.datalayer.repository.datasource.PhotosDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PhotosDataRepositoryTest {
    @Mock
    private PhotosDataStoreFactory photosDataStoreFactory;
    @Mock
    private PhotosMapper photosMapper;
    @Mock
    private PhotosDataStore photosDataStore;

    private PhotosDataRepository photosDataRepository;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photosDataRepository = new PhotosDataRepository(photosDataStoreFactory,photosMapper);
        given(photosDataStoreFactory.create()).willReturn(photosDataStore);
    }
    @Test
    public void getPhotosTest(){
        PhotosEntity photosEntity = new PhotosEntity();
        given(photosDataStore.photos()).willReturn(Observable.just(photosEntity));
        photosDataRepository.photos();
        verify(photosDataStoreFactory).create();
        verify(photosDataStore).photos();
    }


}
