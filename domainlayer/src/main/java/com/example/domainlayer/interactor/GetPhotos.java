package com.example.domainlayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.model.Photos;
import com.example.domainlayer.repository.PhotosRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving an {@link Photos}.
 */
public class GetPhotos extends UseCase<Photos,ApiParam> {
    private final PhotosRepository photosRepository;

    @Inject
    public GetPhotos(PhotosRepository photosRepository, ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.photosRepository = photosRepository;
    }

    @Override
    public Observable<Photos> buildUseCaseObservable(ApiParam apiParam) {
        return this.photosRepository.photos(apiParam.getMethod(),apiParam.getParam1(),apiParam.param2);
    }
}
