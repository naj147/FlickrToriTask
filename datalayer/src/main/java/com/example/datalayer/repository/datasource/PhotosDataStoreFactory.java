package com.example.datalayer.repository.datasource;

import com.example.datalayer.cache.PhotosCache;
import com.example.datalayer.net.FlickrServiceGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Factory that creates different implementations of {@link PhotosDataStore}.
 */
@Singleton
public class PhotosDataStoreFactory {
    private final PhotosCache photosCache;
    private final FlickrServiceGenerator flickrServiceGenerator;


    @Inject
    public PhotosDataStoreFactory(PhotosCache photosCache, FlickrServiceGenerator flickrServiceGenerator) {
        this.photosCache = photosCache;
        this.flickrServiceGenerator = flickrServiceGenerator;
    }

    /**
     * Creates a {@link PhotosLocalDataStore} if the cache isn't expired and it's not empty Else
     * Creates a {@link PhotosCloudDataStore}.
     */
    public PhotosDataStore create(){
        if(!photosCache.isExpired() && photosCache.isCached()){
            return new PhotosLocalDataStore(photosCache);
        }else{
            return new PhotosCloudDataStore(flickrServiceGenerator.getAPI(),photosCache);
        }
    }
}
