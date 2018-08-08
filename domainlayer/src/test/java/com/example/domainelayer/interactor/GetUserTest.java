package com.example.domainelayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.GetUser;
import com.example.domainlayer.repository.PhotosRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserTest {

    GetUser getUser;
    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private PhotosRepository mockPhotosRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        getUser =new GetUser(mockPhotosRepository,mockThreadExecutor,mockPostExecutionThread);
    }

    @Test
    public void getUserBuildUserCaseObservableTest(){
        ApiParam apiParam = mock(ApiParam.class);
        getUser.buildUseCaseObservable(apiParam);
        verify(mockPhotosRepository).userDetails(apiParam.getMethod(),apiParam.getParam1());
        verifyNoMoreInteractions(mockPhotosRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
