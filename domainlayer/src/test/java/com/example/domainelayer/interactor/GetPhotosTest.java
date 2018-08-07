package com.example.domainelayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.GetPhotos;
import com.example.domainlayer.repository.PhotosRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetPhotosTest {

    private GetPhotos getPhotos;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private PhotosRepository mockPhotosRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        getPhotos = new GetPhotos(mockPhotosRepository,mockThreadExecutor,mockPostExecutionThread);
    }

    @Test
    public  void getPhotosBuildUseCaseObservableTest(){
        ApiParam apiParam = mock(ApiParam.class);
        getPhotos.buildUseCaseObservable(apiParam);
        verify(mockPhotosRepository).photos(apiParam.getMethod(),apiParam.getTags(),apiParam.getTitle());
        verifyNoMoreInteractions(mockPhotosRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
