package com.example.naj_t.flickrtoritask.components;

import android.content.Context;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.repository.PhotosRepository;
import com.example.naj_t.flickrtoritask.MainActivity;
import com.example.naj_t.flickrtoritask.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    PhotosRepository photosRepository();
}
