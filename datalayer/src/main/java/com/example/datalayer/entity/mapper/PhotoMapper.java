package com.example.datalayer.entity.mapper;

import com.example.datalayer.entity.PhotoEntity;
import com.example.domainlayer.model.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmList;

/**
 * Mapper class used to transform {@link PhotoEntity} (in the data layer) to {@link Photo} in the
 * domain layer.
 */

@Singleton
public class PhotoMapper {
    @Inject
    public PhotoMapper() {
    }

    /**
     * Transform a {@link PhotoEntity} into an {@link Photo}.
     *
     * @param photoEntity Object to be transformed.
     * @return {@link Photo} if valid {@link PhotoEntity} otherwise null.
     */
    public Photo transform(PhotoEntity photoEntity){
        Photo photo = null;
        if(photoEntity!=null){
            //Photo(String id, String owner, String secret, String server, String farm, String title, int isPublic, int isFriend, int isFamily)
            photo = new Photo(photoEntity.getId(),photoEntity.getOwner(),photoEntity.getSecret(),photoEntity.getServer(),photoEntity.getFarm(),photoEntity.getTitle(),photoEntity.getIsPublic(),photoEntity.getIsFriend(),photoEntity.getIsFamily());
        }
        return  photo;
    }

    /**
     * Transform a {@link RealmList<PhotoEntity>} into an {@link List<Photo>}.
     *
     * @param photoEntities a {@link RealmList<PhotoEntity>} to be transformed.
     * @return {@link List<Photo>}
     */
    public List<Photo> transformList(RealmList<PhotoEntity> photoEntities){
        List<Photo> photoList = new ArrayList<>();
        for(PhotoEntity photoEntity : photoEntities){
            if(photoEntity!=null){
                photoList.add(transform(photoEntity));
            }
        }
        return photoList ;
    }
}
