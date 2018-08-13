package com.example.domainlayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.model.User;
import com.example.domainlayer.repository.PhotosRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a {@link User}.
 */
public class GetUser extends UseCase<User,ApiParam> {

    private final PhotosRepository photosRepository;

    @Inject
    public GetUser(PhotosRepository photosRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.photosRepository = photosRepository;
    }

    @Override
    public Observable<User> buildUseCaseObservable(ApiParam apiParam) {
        return this.photosRepository.userDetails(apiParam.getMethod(),apiParam.param1);
    }
}
