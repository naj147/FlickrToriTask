package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.net.FlickrServiceGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PhotosDataStoreFactoryTest {

    @Mock
    private PhotosCache photosCache;
    @Mock
    private FlickrServiceGenerator flickrServiceGenerator;

    private PhotosDataStoreFactory photosDataStoreFactory;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photosDataStoreFactory = new PhotosDataStoreFactory(photosCache,flickrServiceGenerator);
    }
    @Test
    public void createPhotosLocalStoreTest(){
        given(photosCache.isCached()).willReturn(true);
        given(photosCache.isExpired()).willReturn(false);
        PhotosDataStore photosDataStore = photosDataStoreFactory.create();
        assertThat(photosDataStore).isNotNull();
        assertThat(photosDataStore).isInstanceOf(PhotosLocalDataStore.class);
        verify(photosCache).isCached();
        verify(photosCache).isExpired();
    }

    @Test
    public void createPhotosCloudStoreTest(){
        given(photosCache.isExpired()).willReturn(true);
        given(photosCache.isCached()).willReturn(false);
        PhotosDataStore photosDataStore = photosDataStoreFactory.create();
        assertThat(photosDataStore).isNotNull();
        assertThat(photosDataStore).isInstanceOf(PhotosCloudDataStore.class);
        verify(photosCache).isExpired();
    }


}
