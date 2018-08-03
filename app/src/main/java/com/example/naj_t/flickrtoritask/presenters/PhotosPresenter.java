package com.example.naj_t.flickrtoritask.presenters;

import com.example.domainlayer.exception.DefaultErrorBundle;
import com.example.domainlayer.exception.ErrorBundle;
import com.example.domainlayer.interactor.DefaultObserver;
import com.example.domainlayer.interactor.GetPhotos;
import com.example.domainlayer.model.Photos;
import com.example.naj_t.flickrtoritask.models.Mapper.PhotosModelMapper;
import com.example.naj_t.flickrtoritask.models.PhotosModel;
import com.example.naj_t.flickrtoritask.view.PhotosListView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PhotosPresenter implements Presenter {


    private PhotosListView photosListView;
    private final GetPhotos getPhotosUseCase;
    private final PhotosModelMapper photosModelMapper;

    @Inject
    public PhotosPresenter(GetPhotos getPhotosUseCase, PhotosModelMapper photosModelMapper) {
        this.getPhotosUseCase = getPhotosUseCase;
        this.photosModelMapper = photosModelMapper;
    }

    public void setView(@NotNull PhotosListView view) {
        this.photosListView = view;
    }

    private void showViewLoading() {
        this.photosListView.showLoading();
    }

    private void hideViewLoading() {
        this.photosListView.hideLoading();
    }

    private void showViewRetry() {
        this.photosListView.showRetry();
    }

    private void hideViewRetry() {
        this.photosListView.hideRetry();
    }

    public void loadPhotos() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPhotos();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = "Error In Presenter";
        this.photosListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(Photos photos) {
        final PhotosModel photosModelCollection = this.photosModelMapper.transform(photos);
        this.photosListView.renderPhotosList(photosModelCollection);
    }

    private void getPhotos() {
        this.getPhotosUseCase.execute(new PhotosObserver(), null);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getPhotosUseCase.dispose();
        this.photosListView = null;
    }

    private final class PhotosObserver extends DefaultObserver<Photos> {

        @Override
        public void onComplete() {
            PhotosPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            PhotosPresenter.this.hideViewLoading();
            PhotosPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PhotosPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Photos photos) {
            PhotosPresenter.this.showUsersCollectionInView(photos);
        }
    }
}
