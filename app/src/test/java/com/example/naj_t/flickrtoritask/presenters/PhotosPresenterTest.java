package com.example.naj_t.flickrtoritask.presenters;

import android.content.Context;

import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.DefaultObserver;
import com.example.domainlayer.interactor.GetPhotos;
import com.example.naj_t.flickrtoritask.models.Mapper.PhotosModelMapper;
import com.example.naj_t.flickrtoritask.view.PhotosListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class PhotosPresenterTest {
//    @Mock private ApiParam mockApiParam;
    @Mock private Context mockContext;
    @Mock private PhotosListView mockPhotosListView;
    @Mock private GetPhotos mockGetPhotos;
    @Mock private PhotosModelMapper mockPhotosModelMapper;

    private PhotosPresenter photosPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photosPresenter = new PhotosPresenter(mockGetPhotos,mockPhotosModelMapper);
        photosPresenter.setView(mockPhotosListView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPhotosPresenterLoader(){
//        given(mockApiParam.getMethod()).willReturn(0);
        given(mockPhotosListView.context()).willReturn(mockContext);
        photosPresenter.loadPhotos(null, -1);
        verify(mockPhotosListView).hideRetry();
        verify(mockPhotosListView).showLoading();
        verify(mockGetPhotos).execute(any(DefaultObserver.class), any(ApiParam.class));
    }
}
