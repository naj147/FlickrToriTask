package com.example.domainlayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.model.Photos;
import com.example.domainlayer.repository.PhotosRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPhotos extends UseCase<Photos,Void> {
    private final PhotosRepository photosRepository;

    @Inject
    GetPhotos(PhotosRepository photosRepository, ThreadExecutor threadExecutor,
              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.photosRepository = photosRepository;
    }

    @Override
    Observable<Photos> buildUseCaseObservable(Void Unused) {
        return this.photosRepository.photos();
    }
}
