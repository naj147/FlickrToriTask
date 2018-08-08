package com.example.naj_t.flickrtoritask.presenters;

import android.content.Context;

import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.DefaultObserver;
import com.example.domainlayer.interactor.GetUser;
import com.example.naj_t.flickrtoritask.models.Mapper.UserModelMapper;
import com.example.naj_t.flickrtoritask.view.PhotosDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PhotoDetailPresenterTest {
    @Mock
    private Context mockContext;
    @Mock private PhotosDetailsView mockPhotosDetailsView;
    @Mock private GetUser mockGetUser;
    @Mock private UserModelMapper mockUserModelMapper;
    private PhotoDetailPresenter photoDetailPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photoDetailPresenter = new PhotoDetailPresenter(mockGetUser,mockUserModelMapper);
        photoDetailPresenter.setView(mockPhotosDetailsView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPhotosPresenterLoader(){
//        given(mockApiParam.getMethod()).willReturn(0);
        given(mockPhotosDetailsView.context()).willReturn(mockContext);
        photoDetailPresenter.loadPhotoDetails(null);
        verify(mockPhotosDetailsView).hideRetry();
        verify(mockPhotosDetailsView).showLoading();
        verify(mockGetUser).execute(any(DefaultObserver.class), any(ApiParam.class));
    }
}
