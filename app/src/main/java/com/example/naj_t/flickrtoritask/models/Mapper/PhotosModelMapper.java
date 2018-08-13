package com.example.naj_t.flickrtoritask.models.Mapper;

import com.example.domainlayer.model.Photos;
import com.example.naj_t.flickrtoritask.models.PhotosModel;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Photos} (in the domain layer) to {@link PhotosModel} in the
 * presentation layer.
 */
public class PhotosModelMapper {
    @Inject
    public PhotosModelMapper() {
    }

    /**
     * Transform a {@link Photos} into an {@link PhotosModel}.
     *
     * @param photosEntity Object to be transformed.
     * @return {@link PhotosModel}.
     */
    public PhotosModel transform(Photos photosEntity){
        PhotoModelMapper photoModelMapper = new PhotoModelMapper();
        PhotosModel photosModel = null;
        if(photosEntity!=null){
            photosModel = new PhotosModel(photosEntity.getPage(),photosEntity.getPages(),photosEntity.getTotal(),photosEntity.getLastUpdated(),photoModelMapper.transformList(photosEntity.getPhoto()));
        }
        return  photosModel;
    }
}
