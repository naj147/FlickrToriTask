package com.example.naj_t.flickrtoritask.model.Mapper;

import com.example.domainlayer.model.Photo;
import com.example.naj_t.flickrtoritask.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoModelMapper {

    public PhotoModelMapper() {
    }

    public PhotoModel transform(Photo photoEntity){
        PhotoModel photo = null;

        if(photoEntity!=null){
            //Photo(String id, String owner, String secret, String server, String farm, String title, int isPublic, int isFriend, int isFamily)
            photo = new PhotoModel(photoEntity.getId(),photoEntity.getOwner(),photoEntity.getSecret(),photoEntity.getServer(),photoEntity.getFarm(),photoEntity.getTitle(),photoEntity.getIsPublic(),photoEntity.getIsFriend(),photoEntity.getIsFamily());
        }
        return  photo;
    }





    public List<PhotoModel> transformList(List<Photo> photoEntities){
        List<PhotoModel> photoList = new ArrayList<>();
        for(Photo photoEntity : photoEntities){
            if(photoEntity!=null){
                photoList.add(transform(photoEntity));
            }
        }
        return photoList ;
    }
}
