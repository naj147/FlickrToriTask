package com.example.datalayer.entity.mapper;

import com.example.datalayer.entity.PhotosEntity;
import com.example.domainlayer.model.Photos;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link PhotosEntity} (in the data layer) to {@link Photos} in the
 * domain layer.
 */
@Singleton
public class PhotosMapper {
    @Inject
    public PhotosMapper() {
    }

    /**
     * Transform a {@link PhotosEntity} into an {@link Photos}.
     *
     * @param photosEntity Object to be transformed.
     * @return {@link Photos} if valid {@link PhotosEntity} otherwise null.
     */
    public Photos transform(PhotosEntity photosEntity){
        PhotoMapper photoMapper = new PhotoMapper();
        Photos photos = null;
        //     Photos(int page, int pages, int total, String lastUpdated, ArrayList<Photo> photo)
        if(photosEntity!=null){
            photos = new Photos(photosEntity.getPage(),photosEntity.getPages(),photosEntity.getTotal(),photosEntity.getLastUpdated(),photoMapper.transformList(photosEntity.getPhoto()));
        }
        return  photos;
    }
}
