package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.net.API;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;

public class PhotosCloudDataStoreTest {
    @Mock
    API mockedApiService;
    @Mock
    PhotosCache mockedPhotosCache;

    PhotosCloudDataStore photosCloudDataStore ;

    private  final String METHOD = "flickr.photos.getRecent";
    private  final String api_key= "b59eaa142fbb03d0ba6c93882fd62e30";
    private  final int page=1;
    private final int njcb = 1;
    private final String format = "json";

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        photosCloudDataStore = new PhotosCloudDataStore(mockedApiService, mockedPhotosCache);
    }
    @Test
    public void testGetPhotosFromApi(){
        PhotosEntity fakePhotosEntity = new PhotosEntity();
        io.reactivex.Observable<PhotosEntity> fakeObservable = io.reactivex.Observable.just(fakePhotosEntity);
        given(mockedApiService.listImages(METHOD,api_key,page,format,njcb)).willReturn(fakeObservable);
    }

}
