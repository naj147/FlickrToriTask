package com.example.naj_t.flickrtoritask.DPINJ.components;

import android.content.Context;

import com.example.datalayer.cache.PhotosCache;
import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.repository.PhotosRepository;
import com.example.naj_t.flickrtoritask.view.MainActivity;
import com.example.naj_t.flickrtoritask.DPINJ.modules.ApplicationModule;
import com.squareup.picasso.Picasso;

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
    PhotosCache photoCache();
    Picasso picasso();
}
