package com.example.naj_t.flickrtoritask.presenters;

import com.example.domainlayer.exception.DefaultErrorBundle;
import com.example.domainlayer.exception.ErrorBundle;
import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.DefaultObserver;
import com.example.domainlayer.interactor.GetPhotos;
import com.example.domainlayer.model.Photos;
import com.example.naj_t.flickrtoritask.models.Mapper.PhotosModelMapper;
import com.example.naj_t.flickrtoritask.models.PhotosModel;
import com.example.naj_t.flickrtoritask.view.PhotosListView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
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

    /**
     * Initializes the presenter by start retrieving the photos list.
     *
     * @param text a string representing the title and tags of the photo to be retrieved
     * @param page int the page number to be retrieved
     */
    public void loadPhotos(String text, int page) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPhotos(text, page);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = "Error In Presenter";
        this.photosListView.showError(errorMessage);
    }

    private void showPhotosCollectionInView(Photos photos) {
        final PhotosModel photosModelCollection = this.photosModelMapper.transform(photos);
        this.photosListView.renderPhotosList(photosModelCollection);
    }

    private void getPhotos() {
        getPhotos(1);
    }

    /**
     * A method that retrieves Photos from the backend
     *
     * @param text a string representing the title and tags of the photo to be retrieved
     * @param page int the page number to be retrieved
     */
    private void getPhotos(String text, int page) {
        ApiParam apiParam = new ApiParam();
        if (page < 1)
            apiParam.setParam2(1);
        apiParam.setParam1(text);
        if (text != null && !text.isEmpty())
            apiParam.setMethod(2);
        this.getPhotosUseCase.execute(new PhotosObserver(),apiParam );
    }

    /**
     * A method that retrieves Photos from the backend
     *
     * @param page int the page number to be retrieved
     */
    private void getPhotos(int page) {
        if(page<1)
            page=1;
        ApiParam apiParam = new ApiParam();
        apiParam.setParam2(page);
        this.getPhotosUseCase.execute(new PhotosObserver(),apiParam );
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
            PhotosPresenter.this.showPhotosCollectionInView(photos);
        }
    }
}
