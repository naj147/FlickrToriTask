package com.example.naj_t.flickrtoritask.presenters;

import com.example.domainlayer.exception.DefaultErrorBundle;
import com.example.domainlayer.exception.ErrorBundle;
import com.example.domainlayer.interactor.ApiParam;
import com.example.domainlayer.interactor.DefaultObserver;
import com.example.domainlayer.interactor.GetUser;
import com.example.naj_t.flickrtoritask.models.Mapper.UserModelMapper;
import com.example.naj_t.flickrtoritask.models.UserModel;
import com.example.naj_t.flickrtoritask.view.PhotosDetailsView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;



public class PhotoDetailPresenter implements Presenter {


    private  PhotosDetailsView photosDetailsView;
    private final  GetUser getUser;
    private  final UserModelMapper userModelMapper;

    @Inject
    public PhotoDetailPresenter( GetUser getUser, UserModelMapper userModelMapper) {
        this.getUser = getUser;
        this.userModelMapper = userModelMapper;
    }

    public void setView(@NotNull PhotosDetailsView view){
        this.photosDetailsView= view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getUser.dispose();
        this.photosDetailsView=null;
    }
    public void loadPhotoDetails(String userID) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUser(userID);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = "Error In Presenter";
        this.photosDetailsView.showError(errorMessage);
    }

    private void getUser(String userID) {
        ApiParam apiParam = new ApiParam();
        apiParam.setParam1(userID);
        this.getUser.execute(new UserObserver(),apiParam);
    }
    private void showPhotoDetailsInView(com.example.domainlayer.model.User user) {
        final UserModel userModel = this.userModelMapper.transfrom(user);
        this.photosDetailsView.renderPhoto(userModel);
    }

    private void showViewLoading() {
        this.photosDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.photosDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.photosDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.photosDetailsView.hideRetry();
    }


    private final class UserObserver extends DefaultObserver<com.example.domainlayer.model.User> {

        @Override
        public void onComplete() {
            PhotoDetailPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            PhotoDetailPresenter.this.hideViewLoading();
            PhotoDetailPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PhotoDetailPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(com.example.domainlayer.model.User user) {
            PhotoDetailPresenter.this.showPhotoDetailsInView(user);
        }
    }
}
