package com.example.naj_t.flickrtoritask.model.Mapper;

import com.example.domainlayer.model.Photos;
import com.example.naj_t.flickrtoritask.model.PhotosModel;

public class PhotosModelMapper {

    public PhotosModelMapper() {
    }

    public PhotosModel transform(Photos photosEntity){
        PhotoModelMapper photoModelMapper = new PhotoModelMapper();
        PhotosModel photosModel = null;
        if(photosEntity!=null){
            photosModel = new PhotosModel(photosEntity.getPage(),photosEntity.getPages(),photosEntity.getTotal(),photosEntity.getLastUpdated(),photoModelMapper.transformList(photosEntity.getPhoto()));
        }
        return  photosModel;
    }
}
