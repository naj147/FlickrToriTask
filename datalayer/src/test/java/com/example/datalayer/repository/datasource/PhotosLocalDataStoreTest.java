package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class PhotosLocalDataStoreTest {
    @Mock
    PhotosCache photosCache;
    PhotosLocalDataStore photosLocalDataStore;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photosLocalDataStore = new PhotosLocalDataStore(photosCache);
    }
    @Test
    public void getPhotosFromCacheTest(){

        photosLocalDataStore.photos(0,null,null);
        Mockito.verify(photosCache).get();
    }
}
