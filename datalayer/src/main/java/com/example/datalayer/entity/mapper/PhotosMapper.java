package com.example.datalayer.entity.mapper;

import com.example.datalayer.entity.PhotosEntity;
import com.example.domainlayer.model.Photos;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotosMapper {
    @Inject
    public PhotosMapper() {
    }
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
